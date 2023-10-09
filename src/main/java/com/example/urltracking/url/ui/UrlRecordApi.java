package com.example.urltracking.url.ui;

import com.example.urltracking.url.application.UrlRecordService;
import com.example.urltracking.url.dto.UrlRecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlRecordApi {
    private final UrlRecordService urlRecordService;

    @PostMapping("/url")
    public String saveUrl(@RequestBody UrlRecordDto urlRecordDto) {
        return urlRecordService.saveUrlTemp(urlRecordDto);
    }

    @GetMapping("/{url}")
    public int getUrlCount(@PathVariable String url) {
        return urlRecordService.getUrlCount(url);
    }
}
