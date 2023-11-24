package com.shipmonk.testingday;

import com.shipmonk.testingday.apimodel.RatesApiOutput;
import com.shipmonk.testingday.model.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/api/v1/rates"
)
public class ExchangeRatesController {

    private ExchangeRateProvider exchangeRateProvider;

    @Autowired
    public ExchangeRatesController(ExchangeRateProvider exchangeRateProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{day}")
    public ResponseEntity<RatesApiOutput> getRates(@PathVariable("day") String day) {
        Rates rates = exchangeRateProvider.getExchangeRates(day);
        RatesApiOutput ratesApiOutput = new RatesApiOutput(rates);
        return new ResponseEntity<>(
            ratesApiOutput,
            HttpStatus.OK
        );
    }

}
