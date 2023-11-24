package com.shipmonk.testingday.dbcache;

import com.shipmonk.testingday.cache.ExchangeRateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBExchangeRateCache implements ExchangeRateCache {

    private CacheEntryDAO cacheEntryDAO;

    @Autowired
    public DBExchangeRateCache(CacheEntryDAO cacheEntryDAO) {
        this.cacheEntryDAO = cacheEntryDAO;
    }

    @Override
    public CacheEntry get(String date) {
        return cacheEntryDAO.getCacheEntry(date);
    }

    @Override
    public void upsert(String date, String payload) {
        cacheEntryDAO.upsertCacheEntry(date, payload);
    }

    @Override
    public boolean isInCache(String date) {
        // TODO optimise
        CacheEntry cacheEntry = get(date);
        return cacheEntry != null;
    }

}
