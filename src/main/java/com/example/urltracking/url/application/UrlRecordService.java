package com.example.urltracking.url.application;

import com.example.urltracking.url.application.factory.IdGenerator;
import com.example.urltracking.url.domain.UrlRecordTemp;
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
    private final IdGenerator idGenerator;

    @Transactional
    public UrlRecordTemp saveUrlTemp(String url) {
        final String generatedId = idGenerator.generateId(url);

        Optional<UrlRecordTemp> urlRecord = urlRecordTempRepository.findById(generatedId);
        // 조회된 url이 있으면 count 증가
        if (urlRecord.isPresent()) {
            UrlRecordTemp urlRecordTemp = urlRecord.get();
            if (!urlRecordTemp.getUrl().equals(url)) {
                return retrySaveUrl(url);
            }
            urlRecordTemp.increaseCount();
            urlRecordTempRepository.save(urlRecordTemp);
            return urlRecordTemp;
        }
        // 조회된 url이 없으면 새로 생성
        UrlRecordTemp urlRecordTemp = new UrlRecordTemp(generatedId, url, 1);
        urlRecordTempRepository.save(urlRecordTemp);
        return urlRecordTemp;
    }

    @Transactional(readOnly = true)
    public Integer getUrlCount(String url) {
        final String generatedId = idGenerator.generateId(url);
        Optional<UrlRecordTemp> urlRecord = urlRecordTempRepository.findById(generatedId);
        if (urlRecord.isPresent()) {
            return urlRecord.get().getCount();
        }
        return 0;
    }

    private UrlRecordTemp retrySaveUrl(String url) {
        String id = idGenerator.generateIdWithRandom(url);
        UrlRecordTemp urlRecordTemp = new UrlRecordTemp(id, url, 1);
        urlRecordTempRepository.save(urlRecordTemp);
        return urlRecordTemp;
    }
}
