package tech.form3.igorg.interview.application.exception.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Util class for exceptions.
 */
@UtilityClass
public final class ExceptionUtil {

    private static final int TOKEN_LENGTH = 10;
    private static final RandomStringGenerator RANDOM_STRING_GENERATOR =
            new RandomStringGenerator.Builder().withinRange('A', 'Z').build();

    /**
     * Creates a random error token, used as a reference between log files and
     * messages displayed to the client.
     *
     * @return random error token
     */
    public static String createRandomErrorToken() {
        return RANDOM_STRING_GENERATOR.generate(TOKEN_LENGTH);
    }

}