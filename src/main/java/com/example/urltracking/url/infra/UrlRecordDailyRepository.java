package com.example.urltracking.url.infra;

import com.example.urltracking.url.domain.UrlRecordDaily;
import org.springframework.data.repository.CrudRepository;

public interface UrlRecordDailyRepository extends CrudRepository<UrlRecordDaily, Long> {
}
