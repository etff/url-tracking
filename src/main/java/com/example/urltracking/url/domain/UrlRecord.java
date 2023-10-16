package com.example.urltracking.url.domain;

import com.example.urltracking.global.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "url_record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UrlRecord extends BaseEntity {

    @Id
    @Column(name = "url_record_id")
    private String id;

    @NotNull
    private String originalUrl;

    private Integer totalCount;

    @OneToMany(mappedBy = "urlRecord", cascade = CascadeType.ALL)
    private List<UrlRecordDaily> urlRecordDailies = new ArrayList<>();

    public UrlRecord(String id, String url, Integer totalCount) {
        this.id = id;
        this.originalUrl = url;
        this.totalCount = totalCount;
        this.urlRecordDailies = new ArrayList<>();
    }

    /**
     * 연관관계 편의 메서드.
     */
    public void addUrlRecordDaily(UrlRecordDaily urlRecordDaily) {
        this.urlRecordDailies.add(urlRecordDaily);
        urlRecordDaily.setUrlRecord(this);
    }

    public void increaseCount(int count) {
        this.totalCount += count;
    }
}
