package com.shipmonk.testingday.cache;

import com.shipmonk.testingday.dbcache.CacheEntry;

public interface ExchangeRateCache {

    /**
     * Returns instance from the cache if present.
     *
     * @param date
     * @return Cached instance if present, otherwise null.
     */
    CacheEntry get(String date);

    /**
     * Create a new entry in cache or update the existing one.
     *
     * @param date
     * @param payload
     */
    void upsert(String date, String payload);

    /**
     * Test if the instance is in cache
     *
     * @param date
     * @return true if it is in cache, otherwise false
     */
    boolean isInCache(String date);

}
