package com.example.urltracking.url.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@RedisHash(value = "url_record", timeToLive = 86400)
public class UrlRecordTemp implements Serializable {

    @Id
    private String id;
    private String url;
    private Integer totalCount;

    public UrlRecordTemp(String id, String url, Integer totalCount) {
        this.id = id;
        this.url = url;
        this.totalCount = totalCount;
    }

    public void increaseCount() {
        this.totalCount += 1;
    }

    public UrlRecord toUrlRecord() {
        return new UrlRecord(this.id, this.url, this.totalCount);
    }

    public UrlRecordDaily toUrlRecordDaily() {
        return new UrlRecordDaily(this.totalCount, LocalDate.now());
    }
}
