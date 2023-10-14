package com.example.urltracking.global.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpUtilTest {

    @Test
    void replace_https() {
        String given = "https://www.naver.com";
        String expected = "http://www.naver.com";

        String actual = HttpUtil.replaceHttps(given);

        assertThat(actual).isEqualTo(expected);
    }
}
