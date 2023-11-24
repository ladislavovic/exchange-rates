package com.shipmonk.testingday.dbcache;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CacheEntryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CacheEntry getCacheEntry(String date) {
        return entityManager.find(CacheEntry.class, date);
    }

    @Transactional
    public void upsertCacheEntry(String date, String payload) {
        CacheEntry cacheEntry = getCacheEntry(date);
        if (cacheEntry == null) {
            cacheEntry = new CacheEntry();
            cacheEntry.setDate(date);
            cacheEntry.setPayload(payload);
            cacheEntry.setStoreTimestamp(System.currentTimeMillis());
            entityManager.persist(cacheEntry);
        } else {
            cacheEntry.setPayload(payload);
        }
    }



}
