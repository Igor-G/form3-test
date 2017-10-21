package tech.form3.igorg.interview.application.exception.util;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link ExceptionUtil}.
 */
public class ExceptionUtilTest {

    @Test
    public void shouldCreateNewRandomErrorToken() {
        // given & when
        String randomErrorToken = ExceptionUtil.createRandomErrorToken();

        // then
        assertThat(randomErrorToken.length(), equalTo(10));
        assertThat(Pattern.compile("[A-Z]{10}").matcher(randomErrorToken).matches(), equalTo(true));
    }
}