package com.example.urltracking.url.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Table(name = "url_record_daily")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UrlRecordDaily {

    @Id
    @Column(name = "url_record_daily_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalCount;

    /**
     * 기록일.
     */
    private LocalDate recordDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_record_id")
    private UrlRecord urlRecord;
}
