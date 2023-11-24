package com.shipmonk.testingday.apimodel;

import com.shipmonk.testingday.model.Rate;

public class RateApiOutput {

    private String currencyCode;

    private double rate;


    public RateApiOutput(Rate rate) {
        this.currencyCode = rate.getCurrencyCode();
        this.rate = rate.getRate();
    }

    public RateApiOutput(String currencyCode, double rate) {
        this.currencyCode = currencyCode;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
