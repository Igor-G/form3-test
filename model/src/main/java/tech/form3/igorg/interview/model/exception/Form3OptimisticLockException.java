package tech.form3.igorg.interview.model.exception;

import static java.util.Arrays.asList;
import static tech.form3.igorg.interview.model.exception.errorreason.GeneralErrorReason.OPTIMISTIC_LOCK_FAILURE;

/**
 * Form3 optimistic lock exception.
 * Should be thrown when some resource with a different version than the one in the DB is being requested.
 */
public class Form3OptimisticLockException extends Form3Exception {

    /**
     * Constructor with the type and the id of the resource that was not found.
     *
     * @param resource the resource type
     * @param id       the id of the resource
     */
    public Form3OptimisticLockException(String resource, String id) {
        super(OPTIMISTIC_LOCK_FAILURE, asList(resource, id));
    }

}
