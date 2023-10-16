package com.example.urltracking.url.ui;

import com.example.urltracking.url.application.UrlRecordService;
import com.example.urltracking.url.dto.UrlCountsResponseDto;
import com.example.urltracking.url.dto.UrlRecordDto;
import com.example.urltracking.url.dto.UrlStatisticResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "URL 기록", description = "URL 기록 API")
@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlRecordApi {
    private final UrlRecordService urlRecordService;

    @Operation(summary = "저장 메서드", description = "URL 저장 메서드입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public String saveUrl(@RequestBody @Valid UrlRecordDto urlRecordDto) {
        return urlRecordService.saveUrlTemp(urlRecordDto.url()).getUrl();
    }

    @Operation(summary = "URL 카운트", description = "URL주소 누적 조회수와 오늘 조회수를 리턴합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UrlCountsResponseDto.class)))
    })
    @PostMapping("/counts")
    public UrlCountsResponseDto getUrlCounts(@RequestBody @Valid UrlRecordDto urlRecordDto) {
        return urlRecordService.getCounts(urlRecordDto.url());
    }

    @Operation(summary = "URL 통계", description = "URL주소 일주일간의 통계를 리턴합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UrlCountsResponseDto.class)))
    })
    @PostMapping("/statistic")
    public List<UrlStatisticResponseDto> getUrlStatistic(@RequestBody @Valid UrlRecordDto urlRecordDto) {
        return urlRecordService.getUrlStatistic(urlRecordDto.url());
    }
}
