package com.example.share.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class RedisAbstractBaseLock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "freeLockScript")
    private RedisScript<Boolean> dellockScript;

    protected  static final String KEY_ID_PREFIX = "share:lockId:";

    protected  static final String KEY_PATTERN_PREFIX = "share:lock:";

    @Value("${common.lock.expireTime}")
    private Integer expireTime;

    @Value("${common.lock.sleepTime}")
    private Long sleepTime;

    protected abstract String getkeyId();

    protected abstract String getkeyPattern();

    protected  String formatKeyPattern(String keyPattern, String lockKeySuffix) {
        if(Objects.isNull(lockKeySuffix) || StringUtils.isBlank(lockKeySuffix)) {
            return keyPattern;
        }
        if(Objects.isNull(keyPattern)) {
            return null;
        }

        if(StringUtils.contains(keyPattern,"%s")) {
            return String.format(keyPattern,lockKeySuffix);
        }
        if(StringUtils.contains(keyPattern,"{0}")) {
            return MessageFormat.format(keyPattern,lockKeySuffix);
        }
        return null;
    }

    private String buildLockKey(String lockKeySuffix) {
        String keyPattern = getkeyPattern();
        Assert.notNull(keyPattern, "keyPattern 获取值为null");
        String formatKeyPattern = formatKeyPattern(keyPattern,lockKeySuffix);
        Assert.notNull(formatKeyPattern,"keyPattern format is null");
        return formatKeyPattern;

    }

    public boolean tryLock(String lockKeySuffix, Long lockId) {
        return tryLock(lockKeySuffix, lockId, 0, TimeUnit.NANOSECONDS);
    }

    public boolean tryLock(String lockKeySuffix, Long lockId, final long timeout, final TimeUnit unit) {

        final String key = buildLockKey(lockKeySuffix);
        final String value = String.valueOf(lockId);
//        redisTemplate.opsForValue().setIfAbsent("BBB","1000",expireTime,unit);
        return redisTemplate.opsForValue().setIfAbsent(key,value,expireTime,unit);
//        return redisTemplate.execute(new RedisCallback<Boolean>() {
//            @Override
//            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                Object conn = redisConnection.getNativeConnection();
//                if(conn instanceof Jedis) {
//                    long nano = System.nanoTime();
//                    do {
//                        String flag = ((Jedis) conn).set(key,value,"NX","EX",expireTime);
//                        if("OK".equals(flag)) {
//                            return Boolean.TRUE;
//                        }
//                        if(timeout == 0) {
//                            break;
//                        }
//
//                        try {
//                            Thread.sleep(sleepTime);
//                        } catch (InterruptedException e) {
//                            break;
//                        }
//                    } while ((System.nanoTime() - nano) < unit.toNanos(timeout));
//                }
//                return Boolean.FALSE;
//            }
//        },true);
    }

    public boolean unlock(String lockKeySuffix, Long lockId) {


        List<String> keyList = new ArrayList<>();
        String value = String.valueOf(lockId);

        keyList.add(buildLockKey(lockKeySuffix));
//        keyList.add("BBB");
        return redisTemplate.execute(dellockScript, keyList, value);
    }
}
