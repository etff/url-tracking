package com.example.urltracking.url.application.factory;

import com.example.urltracking.global.util.Base62Encoder;
import com.example.urltracking.global.util.HttpUtil;
import com.example.urltracking.global.util.Md5Encoder;
import org.springframework.stereotype.Component;

@Component
public class UrlIdGenerator implements IdGenerator {

    @Override
    public String generateId(String url) {
        String encode = Md5Encoder.encode(HttpUtil.replaceHttps(url));
        String encodedId = Base62Encoder.encode(encode.getBytes());
        return encodedId.substring(0, 7);
    }

    @Override
    public String generateIdWithRandom(String url) {
        String encode = Md5Encoder.encodeWithRandomCharacters(HttpUtil.replaceHttps(url));
        String encodedId = Base62Encoder.encode(encode.getBytes());
        return encodedId.substring(0, 7);
    }
}
