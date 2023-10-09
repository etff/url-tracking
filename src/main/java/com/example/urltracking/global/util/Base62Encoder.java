package com.example.urltracking.global.util;

/**
 * BASE62 encoder
 */
public class Base62Encoder {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private Base62Encoder() {
    }

    public static String encode(long value) {
        final StringBuilder sb = new StringBuilder();
        while (value != 0) {
            final int remainder = (int) (value % 62);
            sb.append(ALPHABET.charAt(remainder));
            value /= 62;
        }
        return sb.toString();
    }

    public static int decode(String value) {
        int result = 0;
        int power = 1;
        for (int i = 0; i < value.length(); i++) {
            int digit = ALPHABET.indexOf(value.charAt(i));
            result += digit * power;
            power *= 62;
        }
        return result;
    }
}
