package com.example.urltracking.url.infra;

import com.example.urltracking.url.domain.UrlRecordDaily;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UrlRecordDailyRepository extends CrudRepository<UrlRecordDaily, Long> {

    @Query(value = "SELECT coalesce(sum(URD.totalCount),0) " +
            "FROM UrlRecordDaily URD " +
            "WHERE URD.urlRecord.id = :urlRecordId " +
            "and URD.recordDate " +
            "between :fromDate and :toDate")
    Integer sumTotalCountByRecordDate(
            @Param("urlRecordId") String urlRecordId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
}
