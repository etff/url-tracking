package com.example.urltracking.global.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Base62EncoderTest {

    @Test
    void encode() {
        String given = "https://www.naver.com";
        String actual = Base62Encoder.encode(given.getBytes());

        assertThat(actual).isNotNull();
    }
}
