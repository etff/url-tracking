package com.example.urltracking.url.application;

import com.example.urltracking.url.application.factory.IdGenerator;
import com.example.urltracking.url.domain.UrlRecordTemp;
import com.example.urltracking.url.infra.UrlRecordDailyRepository;
import com.example.urltracking.url.infra.UrlRecordRepository;
import com.example.urltracking.url.infra.UrlRecordTempRepository;
import com.example.urltracking.util.StubIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UrlRecordServiceTest {
    @Mock
    private UrlRecordTempRepository urlRecordTempRepository;

    @Mock
    private UrlRecordDailyRepository urlRecordDailyRepository;

    @Mock
    private UrlRecordRepository urlRecordRepository;

    private IdGenerator idGenerator;
    private UrlRecordService urlRecordService;

    @BeforeEach
    void setUp() {
        idGenerator = new StubIdGenerator();
        urlRecordService = new UrlRecordService(
                urlRecordTempRepository,
                urlRecordDailyRepository,
                urlRecordRepository,
                idGenerator
        );
    }

    @Test
    void save_url_temp() {
        String givenUrl = "https://www.naver.com";
        String givenId = "aaabbbcccd";
        UrlRecordTemp givenUrlRecordTemp = new UrlRecordTemp(givenId, givenUrl, 1);
        given(urlRecordTempRepository.findById(anyString()))
                .willReturn(Optional.empty());
        given(urlRecordTempRepository.save(any(UrlRecordTemp.class)))
                .willReturn(givenUrlRecordTemp);

        UrlRecordTemp actual = urlRecordService.saveUrlTemp(givenUrl);

        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(givenId),
                () -> assertThat(actual.getUrl()).isEqualTo(givenUrl),
                () -> assertThat(actual.getTotalCount()).isEqualTo(1)
        );
    }

    @Test
    void save_when_exist_url() {
        String givenUrl = "https://www.naver.com";
        String givenId = "aaabbbcccd";
        UrlRecordTemp givenUrlRecordTemp = new UrlRecordTemp(givenId, givenUrl, 1);
        given(urlRecordTempRepository.findById(anyString()))
                .willReturn(Optional.of(givenUrlRecordTemp));
        given(urlRecordTempRepository.save(any(UrlRecordTemp.class)))
                .willReturn(givenUrlRecordTemp);

        UrlRecordTemp actual = urlRecordService.saveUrlTemp(givenUrl);

        assertAll(
                () -> assertThat(actual.getId()).isEqualTo(givenId),
                () -> assertThat(actual.getUrl()).isEqualTo(givenUrl),
                () -> assertThat(actual.getTotalCount()).isEqualTo(2)
        );
    }
}
