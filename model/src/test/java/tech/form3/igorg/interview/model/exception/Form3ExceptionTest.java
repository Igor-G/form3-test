package tech.form3.igorg.interview.model.exception;

import org.junit.Test;
import tech.form3.igorg.interview.model.exception.errorreason.ErrorReason;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link Form3Exception}.
 */
public class Form3ExceptionTest {

    @Test
    public void shouldCreateExceptionWithErrorReason() {
        // given
        String messagePattern = "Custom message pattern";
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.getMessagePattern()).willReturn(messagePattern);

        // when
        Form3Exception form3Exception = new Form3Exception(errorReason);

        // then
        assertThat(form3Exception.getErrorReason(), equalTo(errorReason));
        assertThat(form3Exception.getMessageArguments(), is(nullValue()));
        assertThat(form3Exception.getCause(), is(nullValue()));
    }

    @Test
    public void shouldCreateExceptionWithErrorReasonAndMessageArgs() {
        // given
        String messagePattern = "Custom message pattern";
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.getMessagePattern()).willReturn(messagePattern);
        List<Object> messageArgs = asList("arg1", "arg2");

        // when
        Form3Exception form3Exception = new Form3Exception(errorReason, messageArgs);

        // then
        assertThat(form3Exception.getErrorReason(), equalTo(errorReason));
        assertThat(form3Exception.getMessageArguments(), equalTo(messageArgs));
        assertThat(form3Exception.getCause(), is(nullValue()));
    }

    @Test
    public void shouldCreateExceptionWithErrorReasonMessageArgsAndCause() {
        // given
        String messagePattern = "Custom message pattern";
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.getMessagePattern()).willReturn(messagePattern);
        List<Object> messageArgs = asList("arg1", "arg2");
        Throwable cause = new Throwable();

        // when
        Form3Exception form3Exception = new Form3Exception(errorReason, messageArgs, cause);

        // then
        assertThat(form3Exception.getErrorReason(), equalTo(errorReason));
        assertThat(form3Exception.getMessageArguments(), equalTo(messageArgs));
        assertThat(form3Exception.getCause(), equalTo(cause));
    }

    @Test
    public void shouldCreateProperStringRepresentationOfTheExceptionWithErrorReasonAndMessageArgs() {
        // given
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.toString()).willReturn("ErrorReasonToString");
        given(errorReason.getMessagePattern()).willReturn("Custom message with first argument ''{0}'' and second argument {1}");
        List<Object> messageArgs = asList(1, "arg-1");
        Form3Exception form3Exception = new Form3Exception(errorReason, messageArgs);

        // when
        String stringDescription = form3Exception.toString();

        // then
        assertThat(stringDescription,
                equalTo("Form3Exception[errorReason=ErrorReasonToString,details=Custom message with first argument '1' and second argument arg-1]"));
    }

    @Test
    public void shouldGetMessageWithNoArguments() {
        // given
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.getMessagePattern()).willReturn("Custom message");
        Form3Exception form3Exception = new Form3Exception(errorReason);

        // when
        String message = form3Exception.getMessage();

        // then
        assertThat(message, equalTo("Custom message"));
    }

    @Test
    public void shouldGetMessageWithArguments() {
        // given
        ErrorReason errorReason = mock(ErrorReason.class);
        given(errorReason.getMessagePattern()).willReturn("Custom message with first argument ''{0}'' and second argument {1}");
        List<Object> messageArgs = asList(1, "arg-1");
        Form3Exception form3Exception = new Form3Exception(errorReason, messageArgs);

        // when
        String message = form3Exception.getMessage();

        // then
        assertThat(message, equalTo("Custom message with first argument '1' and second argument arg-1"));
    }

}