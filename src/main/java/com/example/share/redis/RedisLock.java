package com.example.share.redis;

import org.springframework.stereotype.Service;

@Service
public class RedisLock extends RedisAbstractBaseLock {

    private static final String KEY_ID = KEY_ID_PREFIX + "test";
    private static final String KEY_PATTERN = KEY_PATTERN_PREFIX + "test:%s";

    @Override
    protected String getkeyId() {
        return KEY_ID;
    }

    @Override
    protected String getkeyPattern() {
        return KEY_PATTERN;
    }
}
