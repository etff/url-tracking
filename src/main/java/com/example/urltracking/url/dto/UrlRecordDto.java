package com.example.urltracking.url.dto;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record UrlRecordDto(@URL @NotNull String url) {
}
