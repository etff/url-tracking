package com.example.urltracking.url.application;

import com.example.urltracking.url.application.factory.IdGenerator;
import com.example.urltracking.url.domain.UrlRecord;
import com.example.urltracking.url.domain.UrlRecordTemp;
import com.example.urltracking.url.dto.UrlCountsResponseDto;
import com.example.urltracking.url.infra.UrlRecordDailyRepository;
import com.example.urltracking.url.infra.UrlRecordRepository;
import com.example.urltracking.url.infra.UrlRecordTempRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlRecordService {
    private final UrlRecordTempRepository urlRecordTempRepository;
    private final UrlRecordDailyRepository urlRecordDailyRepository;
    private final UrlRecordRepository urlRecordRepository;
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

    /**
     * 일주일간의 url count 조회를 리턴한다 (redis + db).
     *
     * @param url 입력받은 주소.
     * @return 전체 count.
     */
    @Transactional(readOnly = true)
    public UrlCountsResponseDto getCounts(String url) {
        final String generatedId = idGenerator.generateId(url);
        Optional<UrlRecord> urlRecord = urlRecordRepository.findById(generatedId);
        Optional<UrlRecordTemp> todayUrlRecord = urlRecordTempRepository.findById(generatedId);

        if (urlRecord.isEmpty() && todayUrlRecord.isEmpty()) {
            return new UrlCountsResponseDto(0, 0);
        }
        if (urlRecord.isPresent() && todayUrlRecord.isEmpty()) {
            return new UrlCountsResponseDto(0, urlRecord.get().getTotalCount());
        }
        if (urlRecord.isEmpty()) {
            Integer todayCounts = todayUrlRecord.get().getTotalCount();
            return new UrlCountsResponseDto(todayCounts, todayCounts);
        }
        UrlRecordTemp urlRecordTemp = todayUrlRecord.get();
        int todayCounts = urlRecordTemp.getTotalCount();
        int totalCounts = todayCounts + urlRecord.get().getTotalCount();
        return new UrlCountsResponseDto(todayCounts, totalCounts);
    }

    private Integer getSixDaysTotalCounts(String generatedId) {
        return urlRecordDailyRepository.sumTotalCountByRecordDate(
                generatedId,
                LocalDate.now().minusDays(7),
                LocalDate.now().minusDays(1)
        );
    }

    private UrlRecordTemp retrySaveUrl(String url) {
        String id = idGenerator.generateIdWithRandom(url);
        UrlRecordTemp urlRecordTemp = new UrlRecordTemp(id, url, 1);
        urlRecordTempRepository.save(urlRecordTemp);
        return urlRecordTemp;
    }

    @Transactional(readOnly = true)
    public int getUrlStatistic(String url) {
        final String generatedId = idGenerator.generateId(url);
        Integer sixDaysTotal = getSixDaysTotalCounts(generatedId);
        Optional<UrlRecordTemp> urlRecord = urlRecordTempRepository.findById(generatedId);

        return urlRecord.map(urlRecordTemp ->
                sixDaysTotal + urlRecordTemp.getTotalCount()).orElse(sixDaysTotal);
    }
}
