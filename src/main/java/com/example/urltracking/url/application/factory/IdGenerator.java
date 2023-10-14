package com.example.urltracking.url.application.factory;

public interface IdGenerator {
    String generateId(String url);

    String generateIdWithRandom(String url);
}
