package com.example.urltracking.global.util;

public class HttpUtil {

    public static String replaceHttps(String url) {
        return url.replace("https://", "http://");
    }
}
