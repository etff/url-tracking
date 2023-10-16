package com.example.urltracking.url.ui;

import com.example.urltracking.url.application.UrlRecordService;
import com.example.urltracking.url.domain.UrlRecordTemp;
import com.example.urltracking.url.dto.UrlCountsResponseDto;
import com.example.urltracking.url.dto.UrlRecordDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UrlRecordApi.class)
class UrlRecordApiTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UrlRecordService urlRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save_url() throws Exception {
        String url = "https://www.naver.com";
        UrlRecordDto request = new UrlRecordDto(url);
        UrlRecordTemp givenUrlRecordTemp = new UrlRecordTemp("aaabbbcccd", "https://www.naver.com", 1);
        given(urlRecordService.saveUrlTemp(url))
                .willReturn(givenUrlRecordTemp);

        // when & then
        mvc.perform(post("/api/v1/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void get_url_count() throws Exception {
        String url = "https://www.naver.com";
        UrlRecordDto request = new UrlRecordDto(url);
        UrlCountsResponseDto givenUrlCountsResponseDto = new UrlCountsResponseDto(1, 1);

        given(urlRecordService.getCounts(url))
                .willReturn(givenUrlCountsResponseDto);

        mvc.perform(post("/api/v1/url/counts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        objectMapper.writeValueAsString(givenUrlCountsResponseDto)))
                .andDo(MockMvcResultHandlers.print());
    }
}
