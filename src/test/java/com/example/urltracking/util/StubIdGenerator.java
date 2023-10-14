package com.example.urltracking.util;

import com.example.urltracking.url.application.factory.IdGenerator;

public class StubIdGenerator implements IdGenerator {

    @Override
    public String generateId(String url) {
        return "aaabbbcccd";
    }

    @Override
    public String generateIdWithRandom(String url) {
        return "aaabbbcccd";
    }
}
