package com.example.urltracking.url.ui;

import com.example.urltracking.url.application.UrlRecordService;
import com.example.urltracking.url.dto.UrlRecordDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlRecordApi {
    private final UrlRecordService urlRecordService;

    @PostMapping
    public String saveUrl(@RequestBody @Valid UrlRecordDto urlRecordDto) {
        return urlRecordService.saveUrlTemp(urlRecordDto.url()).getUrl();
    }

    @PostMapping("/counts")
    public int getUrlCount(@RequestBody @Valid UrlRecordDto urlRecordDto) {
        return urlRecordService.getUrlCount(urlRecordDto.url());
    }
}
