package com.example.urltracking.url.infra;

import com.example.urltracking.url.domain.UrlRecordDaily;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UrlRecordDailyRepository extends CrudRepository<UrlRecordDaily, Long> {

    @Query(value = "SELECT URD " +
            "FROM UrlRecordDaily URD " +
            "WHERE URD.urlRecord.id = :urlRecordId " +
            "and URD.recordDate " +
            "between :fromDate and :toDate")
    List<UrlRecordDaily> sumTotalCountByRecordDate(
            @Param("urlRecordId") String urlRecordId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
}
