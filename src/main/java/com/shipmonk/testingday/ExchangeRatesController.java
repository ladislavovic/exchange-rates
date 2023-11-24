package com.shipmonk.testingday;

import com.shipmonk.testingday.model.Rate;
import com.shipmonk.testingday.model.RatesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
    path = "/api/v1/rates"
)
public class ExchangeRatesController
{

    @RequestMapping(method = RequestMethod.GET, path = "/{day}")
    public ResponseEntity<RatesOutput> getRates(@PathVariable("day") String day)
    {
        RatesOutput ratesOutput = new RatesOutput();
        ratesOutput.setSuccess(true);
        ratesOutput.setTimestamp(System.currentTimeMillis());
        ratesOutput.setDate(day);
        ratesOutput.setRates(List.of(new Rate("CZK", 24.0), new Rate("AED", 4.5)));


        return new ResponseEntity<>(
            ratesOutput,
            HttpStatus.OK
        );
    }

}
