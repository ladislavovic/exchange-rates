package com.shipmonk.testingday.apimodel;

import com.shipmonk.testingday.model.Rates;

import java.util.List;
import java.util.stream.Collectors;

public class RatesApiOutput {

    private boolean success;

    private long timestamp;

    private String base;

    private String date;

    private List<RateApiOutput> rates;

    public RatesApiOutput(Rates rates) {
        this.success = rates.isSuccess();
        this.timestamp = rates.getTimestamp();
        this.base = rates.getBase();
        this.date = rates.getDate();
        this.rates = rates
            .getRates()
            .stream()
            .map(RateApiOutput::new)
            .collect(Collectors.toList());
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

    public List<RateApiOutput> getRates() {
        return rates;
    }

    public void setRates(List<RateApiOutput> rates) {
        this.rates = rates;
    }
}

