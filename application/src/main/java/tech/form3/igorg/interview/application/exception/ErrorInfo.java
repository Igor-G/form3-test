package tech.form3.igorg.interview.application.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tech.form3.igorg.interview.model.exception.Form3Exception;

import java.time.OffsetDateTime;

import static tech.form3.igorg.interview.model.exception.errorreason.GeneralErrorReason.ERROR;

/**
 * Error Info object returned with error response.
 */
@Data
public class ErrorInfo {

    private String id;
    private String code;
    private String details;
    private OffsetDateTime timestamp = OffsetDateTime.now();

    @JsonIgnore
    private boolean unhandledException;

    /**
     * Constructs ExceptionInfo from a details.
     *
     * @param details the details
     */
    public ErrorInfo(String details) {
        this.details = details;
        this.unhandledException = true;
        this.code = ERROR.toString();
    }

    /**
     * Constructs ExceptionInfo from a Form3Exception.
     *
     * @param ex form3 exception
     */
    public ErrorInfo(Form3Exception ex) {
        this.details = ex.getMessage();
        this.code = ex.getErrorReason().toString();
    }

}
