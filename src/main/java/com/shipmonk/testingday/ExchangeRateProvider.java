package com.shipmonk.testingday;

import com.shipmonk.testingday.model.ExchangeRates;

public interface ExchangeRateProvider {

    /**
     * Provide exchange rate information for the given day.
     *
     * @param date The date you want exchange rates for
     * @return Exchange rates
     */
    ExchangeRates getExchangeRates(String date);

}
