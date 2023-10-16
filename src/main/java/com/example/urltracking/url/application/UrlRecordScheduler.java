package com.example.urltracking.url.application;

import com.example.urltracking.url.domain.UrlRecord;
import com.example.urltracking.url.infra.UrlRecordRepository;
import com.example.urltracking.url.infra.UrlRecordTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UrlRecordScheduler {
    private final UrlRecordTempRepository urlRecordTempRepository;
    private final UrlRecordRepository urlRecordRepository;

    /**
     * 매일 00시 00분 00초에 실행
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void saveUrlRecord() {
        urlRecordTempRepository.findAll().forEach(it -> {

            Optional<UrlRecord> foundUrlRecord = urlRecordRepository.findById(it.getId());
            // 신규 url이면 저장
            if (foundUrlRecord.isEmpty()) {
                UrlRecord urlRecord = it.toUrlRecord();
                urlRecord.addUrlRecordDaily(it.toUrlRecordDaily());
                urlRecordRepository.save(urlRecord);
            }
            // 기존 url이면 count 증가
            if (foundUrlRecord.isPresent()) {
                UrlRecord urlRecord = foundUrlRecord.get();
                urlRecord.increaseCount(it.getTotalCount());
                urlRecord.addUrlRecordDaily(it.toUrlRecordDaily());
            }
            urlRecordTempRepository.delete(it);
        });
    }
}
