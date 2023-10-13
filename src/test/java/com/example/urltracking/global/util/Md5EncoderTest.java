package com.example.urltracking.global.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Md5EncoderTest {

    @Test
    void encode_string_with_md5() {
        String givenString = "aaa";
        String expected = "47bce5c74f589f4867dbd57e9ca9f808";

        String actual = Md5Encoder.encode(givenString);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void encode_with_random_character() {
        String givenString = "aaa";

        String actual = Md5Encoder.encodeWithRandomCharacters(givenString);

        assertThat(actual.length()).isEqualTo(4);
    }
}
