package com.example.urltracking.url.application;

import com.example.urltracking.url.domain.UrlRecordTemp;
import com.example.urltracking.url.dto.UrlRecordDto;
import com.example.urltracking.url.infra.UrlRecordTempRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlRecordService {
    private final UrlRecordTempRepository urlRecordTempRepository;

    @Transactional
    public String saveUrlTemp(UrlRecordDto urlRecordDto) {
        String url = urlRecordDto.url();
        Optional<UrlRecordTemp> urlRecord = urlRecordTempRepository.findById(url);
        if (urlRecord.isPresent()) {
            UrlRecordTemp urlRecordTemp = urlRecord.get();
            urlRecordTemp.increaseCount();
            urlRecordTempRepository.save(urlRecordTemp);
            return urlRecordTemp.getUrl();
        }

        UrlRecordTemp urlRecordTemp = new UrlRecordTemp(url, 1);
        urlRecordTempRepository.save(urlRecordTemp);
        return urlRecordTemp.getUrl();
    }

    @Transactional(readOnly = true)
    public Integer getUrlCount(String url) {
        Optional<UrlRecordTemp> urlRecord = urlRecordTempRepository.findById(url);
        if (urlRecord.isPresent()) {
            return urlRecord.get().getCount();
        }
        return 0;
    }
}
