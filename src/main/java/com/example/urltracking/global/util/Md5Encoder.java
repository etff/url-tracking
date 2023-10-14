package com.example.urltracking.global.util;

import org.springframework.util.DigestUtils;

import java.util.concurrent.ThreadLocalRandom;

public class Md5Encoder {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    private Md5Encoder() {
    }

    /**
     * MD5 인코딩.
     *
     * @param value
     * @return
     */
    public static String encode(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }

    public static String encodeWithRandomCharacters(String value) {
        int index = random.nextInt(ALPHABET.length());
        char randomCharacter = ALPHABET.charAt(index);
        return value + randomCharacter;
    }
}
