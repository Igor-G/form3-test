package tech.form3.igorg.interview.model.exception.errorreason;

/**
 * Error reason.
 *
 * Detailed explanation of the error that happened.
 */
public interface ErrorReason {

    /**
     * Gets the message pattern for the corresponding error reason.
     *
     * @return the message pattern
     */
    String getMessagePattern();
}