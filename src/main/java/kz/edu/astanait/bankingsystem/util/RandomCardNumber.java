package kz.edu.astanait.bankingsystem.util;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCardNumber {

    private final static String DIGITS = "1234567890";
    private final static Random random = new SecureRandom();

    private static char randomDigit() {
        return DIGITS.charAt(random.nextInt(DIGITS.length()));
    }

    public static String getCardNumber() {
        StringBuilder sb = new StringBuilder();
        int space = 0;

        for (int i = 16; i > 0; i--) {
            if (space == 4) {
                sb.append('-');
                space = 0;
            }
            space++;
            sb.append(randomDigit());
        }

        return sb.toString();
    }
}

