package tech.form3.igorg.interview.model.exception.errorreason;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * General error reason.
 *
 * Represents general error reasons that could happen in the application.
 */
@AllArgsConstructor
public enum GeneralErrorReason implements ErrorReason {

    /**
     * Unhandled error.
     */
    ERROR("{0}"),

    /**
     * The requested resource was not found.
     */
    NOT_FOUND("The {0} with id ''{1}'' was not found"),

    /**
     * Optimistic lock failure.
     */
    OPTIMISTIC_LOCK_FAILURE("The provided {0} with id ''{1}'' has a different version than the saved one");

    @Getter
    private String messagePattern;
}