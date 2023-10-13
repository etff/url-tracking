package com.example.urltracking.url.infra;

import com.example.urltracking.url.domain.UrlRecordTemp;
import org.springframework.data.repository.CrudRepository;

public interface UrlRecordTempRepository extends CrudRepository<UrlRecordTemp, String> {
}
