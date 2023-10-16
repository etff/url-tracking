package com.example.urltracking.url.infra;

import com.example.urltracking.url.domain.UrlRecord;
import org.springframework.data.repository.CrudRepository;

public interface UrlRecordRepository extends CrudRepository<UrlRecord, String> {
}
