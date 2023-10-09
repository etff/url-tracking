package com.example.urltracking.url.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value = "url_record", timeToLive = 86400)
public class UrlRecordTemp implements Serializable {

    @Id
    private String url;
    private Integer count;

    public UrlRecordTemp(String url, Integer count) {
        this.url = url;
        this.count = count;
    }

    public void increaseCount() {
        this.count += 1;
    }
}
