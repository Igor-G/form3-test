package tech.form3.igorg.interview.application.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Error rest resource.
 */
@Getter
@Setter
@NoArgsConstructor
public class ErrorResource {

    private List<ErrorInfo> errors;

    /**
     * Constructs the ErrorResource from the errors vararg.
     *
     * @param errors the errors vararg
     */
    public ErrorResource(ErrorInfo... errors) {
        this.errors = asList(errors);
    }
}
