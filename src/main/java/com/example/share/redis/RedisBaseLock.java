package com.example.share.redis;

import java.util.concurrent.TimeUnit;

public interface RedisBaseLock {

    /**
     * @return
     */
    Long nextId();

    /**
     * @param lockKeySuffix
     * @param lockId
     * @return
     */
    boolean tryLock(String lockKeySuffix, Long lockId);

    /**
     * @param lockKeySuffix
     * @param lockId
     * @param timeout
     * @param unit
     * @return
     */
    boolean tryLock(String lockKeySuffix, Long lockId, Long timeout, TimeUnit unit);

    /**
     * @param lockKeySuffix
     * @param lockId
     * @param timeout
     * @param unit
     * @param expire
     * @return
     */
    boolean tryLock(String lockKeySuffix, Long lockId, Long timeout, TimeUnit unit, Long expire);

    /**
     * @param lockKeySuffix
     * @param lockId
     * @return
     */
    boolean lock(String lockKeySuffix, Long lockId);

    /**
     * @param lockKeySuffix
     * @param lockId
     * @return
     */
    boolean unlock(String lockKeySuffix, Long lockId);
}
