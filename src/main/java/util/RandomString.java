package util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {

    private static final Random random = new Random();

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    /**
     * Generate a random string.
     */
    static public String generateRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(alphabet.charAt(random.nextInt(26)));
        }
        return result.toString();
    }
}