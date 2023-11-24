package com.shipmonk.testingday.apimodel;

import com.shipmonk.testingday.model.ExchangeRates;

import java.util.List;

public class RatesApiOutput {

    private boolean success;

    private long timestamp;

    private String base;

    private String date;

    private List<Rate> rates;

    public RatesApiOutput(ExchangeRates exchangeRates) {
        success = exchangeRates.isSuccess();
        timestamp = exchangeRates.getTimestamp();
        base = exchangeRates.getBase();
        date = exchangeRates.getDate();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}

