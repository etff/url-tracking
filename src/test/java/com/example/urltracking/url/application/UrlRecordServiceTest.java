package com.example.urltracking.url.application;

import com.example.urltracking.url.dto.UrlRecordDto;
import com.example.urltracking.url.infra.UrlRecordTempRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlRecordServiceTest {
    @Mock
    private UrlRecordTempRepository urlRecordTempRepository;
    private UrlRecordService urlRecordService;

    @BeforeEach
    void setUp() {
        urlRecordService = new UrlRecordService(urlRecordTempRepository);
    }

    @Test
    void save_url_temp() {
        String givenUrl = "https://www.naver.com";
        UrlRecordDto urlRecordDto = new UrlRecordDto(givenUrl);
        String actual = urlRecordService.saveUrlTemp(urlRecordDto);

        urlRecordService.getUrlCount(givenUrl);

        assertThat(actual).isEqualTo(givenUrl);
    }

    @Test
    void get_url_count() {
        String givenUrl = "https://www.naver.com";
        given(urlRecordTempRepository.findById(givenUrl))
                .willReturn(Optional.of(new com.example.urltracking.url.domain.UrlRecordTemp(givenUrl, 1)));

        int actual = urlRecordService.getUrlCount(givenUrl);

        assertThat(actual).isEqualTo(1);
    }
}
