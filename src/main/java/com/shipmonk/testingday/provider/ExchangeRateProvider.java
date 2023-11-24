package com.shipmonk.testingday.provider;

import com.shipmonk.testingday.model.Rates;

public interface ExchangeRateProvider {

    /**
     * Provide exchange rate information for the given day.
     *
     * @param date The date you want exchange rates for
     * @return Exchange rates
     */
    Rates getExchangeRates(String date) throws CanNotGetExchangeRatesException;

}
