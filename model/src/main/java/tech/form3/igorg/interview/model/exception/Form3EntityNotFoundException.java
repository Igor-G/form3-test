package tech.form3.igorg.interview.model.exception;

import static java.util.Arrays.asList;
import static tech.form3.igorg.interview.model.exception.errorreason.GeneralErrorReason.NOT_FOUND;

/**
 * Form3 entity not found.
 * Should be thrown when some resource has been requested but was not found.
 */
public class Form3EntityNotFoundException extends Form3Exception {

    /**
     * Constructor with the type and the id of the resource that was not found.
     *
     * @param resource the resource type
     * @param id       the id of the resource
     */
    public Form3EntityNotFoundException(String resource, String id) {
        super(NOT_FOUND, asList(resource, id));
    }

}
