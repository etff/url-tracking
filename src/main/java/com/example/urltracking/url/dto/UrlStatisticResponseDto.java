package com.example.urltracking.url.dto;

import com.example.urltracking.url.domain.UrlRecordDaily;

import java.time.LocalDate;

public record UrlStatisticResponseDto(LocalDate recordDate, int totalCount) {
    public UrlStatisticResponseDto(UrlRecordDaily urlRecordDaily) {
        this(urlRecordDaily.getRecordDate(), urlRecordDaily.getTotalCount());
    }
}
