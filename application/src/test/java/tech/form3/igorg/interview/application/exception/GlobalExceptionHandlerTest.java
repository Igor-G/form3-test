package tech.form3.igorg.interview.application.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.form3.igorg.interview.model.exception.Form3EntityNotFoundException;
import tech.form3.igorg.interview.model.exception.Form3Exception;
import tech.form3.igorg.interview.model.exception.Form3OptimisticLockException;
import tech.form3.igorg.interview.model.exception.errorreason.GeneralErrorReason;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static tech.form3.igorg.interview.model.exception.errorreason.GeneralErrorReason.ERROR;

/**
 * Unit tests for {@link GlobalExceptionHandler}.
 */
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    public void shouldHandleGeneralForm3Exception() {
        // given
        Form3Exception form3Exception = new Form3Exception(ERROR);

        // when
        ResponseEntity<ErrorResource> responseEntity = handler.handleForm3Failure(form3Exception);

        // then
        ErrorResource body = responseEntity.getBody();
        ErrorInfo errorInfo = body.getErrors().get(0);
        assertThat(responseEntity.getStatusCode(), equalTo(INTERNAL_SERVER_ERROR));
        assertThat(errorInfo.getCode(), equalTo(ERROR.toString()));
        assertThat(errorInfo.getDetails(), equalTo(""));
        assertErrorReferenceAndTimestamp(errorInfo);
    }

    @Test
    public void shouldHandleEntityNotFoundException() {
        // given
        String resource = "CustomResource";
        String resourceId = "resource-id";
        Form3EntityNotFoundException entityNotFoundException = new Form3EntityNotFoundException(resource, resourceId);

        // when
        ResponseEntity<ErrorResource> responseEntity = handler.handleEntityNotFoundFailure(entityNotFoundException);

        // then
        ErrorResource body = responseEntity.getBody();
        ErrorInfo errorInfo = body.getErrors().get(0);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(errorInfo.getCode(), equalTo(GeneralErrorReason.NOT_FOUND.toString()));
        assertThat(errorInfo.getDetails(), equalTo("The CustomResource with id 'resource-id' was not found"));
        assertErrorReferenceAndTimestamp(errorInfo);
    }

    @Test
    public void shouldHandleOptimisticLockException() {
        // given
        String resource = "CustomResource";
        String resourceId = "resource-id";
        Form3OptimisticLockException optimisticLockException = new Form3OptimisticLockException(resource, resourceId);

        // when
        ResponseEntity<ErrorResource> responseEntity = handler.handleOptimisticLockFailure(optimisticLockException);

        // then
        ErrorResource body = responseEntity.getBody();
        ErrorInfo errorInfo = body.getErrors().get(0);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.PRECONDITION_FAILED));
        assertThat(errorInfo.getCode(), equalTo(GeneralErrorReason.OPTIMISTIC_LOCK_FAILURE.toString()));
        assertThat(errorInfo.getDetails(), equalTo("The provided CustomResource with id 'resource-id' has a different version than the saved one"));
        assertErrorReferenceAndTimestamp(errorInfo);
    }

    @Test
    public void shouldHandleUnknownException() {
        // given
        Exception unknownException = new Exception("Custom message of unknown exception");

        // when
        ResponseEntity<ErrorResource> responseEntity = handler.handleFailure(unknownException);

        // then
        ErrorResource body = responseEntity.getBody();
        ErrorInfo errorInfo = body.getErrors().get(0);
        assertThat(responseEntity.getStatusCode(), equalTo(INTERNAL_SERVER_ERROR));
        assertThat(errorInfo.getCode(), equalTo(ERROR.toString()));
        assertThat(errorInfo.getDetails(), equalTo("Unexpected exception"));
        assertErrorReferenceAndTimestamp(errorInfo);
    }

    private void assertErrorReferenceAndTimestamp(ErrorInfo errorInfo) {
        assertThat(errorInfo.getCode(), notNullValue());
        assertThat(errorInfo.getTimestamp(), notNullValue());
    }

}