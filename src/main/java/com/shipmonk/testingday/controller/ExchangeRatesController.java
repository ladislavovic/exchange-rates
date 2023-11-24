package com.shipmonk.testingday.controller;

import com.shipmonk.testingday.apimodel.RatesApiOutput;
import com.shipmonk.testingday.model.Rates;
import com.shipmonk.testingday.provider.ExchangeRateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private void validateDay(String day) {
        if (!StringUtils.hasText(day)) {
            throw new ValidationException("The 'day' parameter is missing.");
        }

        if (!"latest".equals(day) && !isValidDateFormat(day, "yyyy-MM-dd")) {
            throw new ValidationException("The `day` parameter is in incorrect format. Expected yyyy-MM-dd.");
        }
    }

    private boolean isValidDateFormat(String dateString, String dateFormatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }



}
