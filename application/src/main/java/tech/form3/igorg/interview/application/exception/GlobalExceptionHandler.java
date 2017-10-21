package tech.form3.igorg.interview.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.form3.igorg.interview.application.exception.util.ExceptionUtil;
import tech.form3.igorg.interview.model.exception.Form3EntityNotFoundException;
import tech.form3.igorg.interview.model.exception.Form3Exception;
import tech.form3.igorg.interview.model.exception.Form3OptimisticLockException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

/**
 * Global error handler.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exception handler for Form3 exceptions.
     *
     * @param ex exception
     * @return ErrorResource response entity
     */
    @ExceptionHandler(Form3Exception.class)
    public ResponseEntity<ErrorResource> handleForm3Failure(Form3Exception ex) {
        return handleException(ex, new ErrorInfo(ex), INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for {@link Form3EntityNotFoundException} (and sub-classes).
     *
     * @param ex exception
     * @return ErrorResource response entity
     */
    @ExceptionHandler(Form3EntityNotFoundException.class)
    public ResponseEntity<ErrorResource> handleEntityNotFoundFailure(Form3EntityNotFoundException ex) {
        return handleException(ex, new ErrorInfo(ex), NOT_FOUND);
    }

    /**
     * Exception handler for {@link Form3OptimisticLockException}.
     *
     * @param ex exception
     * @return ErrorResource response entity
     */
    @ExceptionHandler(Form3OptimisticLockException.class)
    public ResponseEntity<ErrorResource> handleOptimisticLockFailure(Form3OptimisticLockException ex) {
        return handleException(ex, new ErrorInfo(ex), PRECONDITION_FAILED);
    }

    /**
     * Exception handler for undeclared exceptions which are not handled by the
     * other exception handler methods.
     *
     * @param ex exception
     * @return ErrorResource response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleFailure(Exception ex) {
        return handleException(ex, new ErrorInfo("Unexpected exception"), INTERNAL_SERVER_ERROR);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return (ResponseEntity) handleException(ex, new ErrorInfo(status.getReasonPhrase()), headers, status);
    }

    private ResponseEntity<ErrorResource> handleException(Throwable ex, ErrorInfo errorInfo, HttpStatus status) {
        return handleException(ex, errorInfo, new HttpHeaders(), status);
    }

    protected final ResponseEntity<ErrorResource> handleException(Throwable ex, ErrorInfo errorInfo, HttpHeaders headers, HttpStatus status) {
        String errorReferenceToken = ExceptionUtil.createRandomErrorToken();
        errorInfo.setId(errorReferenceToken);
        logException(ex, errorReferenceToken, errorInfo);
        return new ResponseEntity<>(new ErrorResource(errorInfo), headers, status);
    }

    /**
     * Logs the exception with the given reference string and a short description.
     *
     * @param ex                  the exception to log
     * @param errorReferenceToken the reference token
     * @param errorInfo           the exception info
     */
    private void logException(Throwable ex, String errorReferenceToken, ErrorInfo errorInfo) {
        String logMessage = "An exception occurred in a service call. Error reference: {}. Description: {}.";
        if (errorInfo.isUnhandledException()) {
            log.warn(logMessage, errorReferenceToken, ex.toString(), ex);
        } else {
            log.info(logMessage, errorReferenceToken, ex.toString(), ex);
        }
    }
}
