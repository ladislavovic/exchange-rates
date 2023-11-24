package com.shipmonk.testingday.dbcache;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CacheEntry {

    /**
     * Exchange Rate date
     */
    @Id
    private String date;

    /** The timestamp the entry was stored to the cache */
    private long storeTimestamp;

    /** The cached data */
    private String payload;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String data) {
        this.payload = data;
    }

}
