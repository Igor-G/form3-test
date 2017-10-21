package tech.form3.igorg.interview.model.exception;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.form3.igorg.interview.model.exception.errorreason.ErrorReason;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * General Form3 exception.
 */
public class Form3Exception extends RuntimeException {

    @Getter
    private final ErrorReason errorReason;

    @Getter
    private final List<Object> messageArguments;

    /**
     * Constructor.
     *
     * @param errorReason error reason
     */
    public Form3Exception(ErrorReason errorReason) {
        this(errorReason, null, null);
    }

    /**
     * Constructor.
     *
     * @param errorReason error reason
     * @param messageArguments message pattern arguments
     */
    public Form3Exception(ErrorReason errorReason, List<Object> messageArguments) {
        this(errorReason, messageArguments, null);
    }

    /**
     * Constructor.
     *
     * @param errorReason error reason
     * @param messageArguments message pattern arguments
     * @param cause cause
     */
    public Form3Exception(ErrorReason errorReason, List<Object> messageArguments, Throwable cause) {
        super(cause);
        this.errorReason = errorReason;
        this.messageArguments = messageArguments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("errorReason", this.errorReason)
                .append("details", getMessage())
                .toString();
    }

    @Override
    public String getMessage() {
        return MessageFormat.format(errorReason.getMessagePattern(), getMessageArgumentsArray());
    }

    private Object[] getMessageArgumentsArray() {
        return messageArguments == null ?
                new String[]{""} : messageArguments.stream().map(Objects::toString).toArray(String[]::new);
    }
}
