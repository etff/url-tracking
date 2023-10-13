package com.example.urltracking.global.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Base62EncoderTest {

    @Test
    void encode() {
        int givenInt = 1000;
        String expected = "iq";

        String actual = Base62Encoder.encode(givenInt);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void decode() {
        String givenString = "iq";
        int expected = 1000;

        int actual = Base62Encoder.decode(givenString);

        assertThat(actual).isEqualTo(expected);
    }
}
