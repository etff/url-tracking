package com.example.urltracking.global.util;

import io.seruco.encoding.base62.Base62;

/**
 * BASE62 encoder
 */
public class Base62Encoder {
    private static Base62 base62 = Base62.createInstance();

    private Base62Encoder() {
    }

    public static String encode(byte[] value) {
        return new String(base62.encode(value));
    }
}
