package com.shipmonk.testingday.fixerioprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipmonk.testingday.cache.ExchangeRateCache;
import com.shipmonk.testingday.provider.CanNotGetExchangeRatesException;
import com.shipmonk.testingday.provider.ExchangeRateProvider;
import com.shipmonk.testingday.model.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FixerIOExchangeRateProvider implements ExchangeRateProvider {

    private String baseUrl;

    private String apiKey;

    private RestTemplate restTemplate;

    private ExchangeRateCache cache;

    @Autowired
    public FixerIOExchangeRateProvider(
        @Value("${fixerio.base-url}") String baseUrl,
        @Value("${fixerio.apikey}") String apiKey,
        RestTemplate restTemplate,
        ExchangeRateCache exchangeRateCache) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
        this.cache = exchangeRateCache;
    }

    @Override
    public Rates getExchangeRates(final String date) {
        try {
            return doGetExchangeRates(date);
        } catch (CanNotGetExchangeRatesException e) {
            throw e;
        } catch (Exception e) {
            throw new CanNotGetExchangeRatesException(e);
        }
    }

    private Rates doGetExchangeRates(final String date) {

        String payload;
        if (cache.isInCache(date)) {
            payload = cache.get(date).getPayload();
        } else {
            payload = getExchangeRatesFromApi(date);

            // TODO "latest" should not be stored to the cache or there should be
            // an cache entry expiration date to make the cache entry invalid
            cache.upsert(date, payload);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new CanNotGetExchangeRatesException(e);
        }

        return new Rates();
    }

    private String getExchangeRatesFromApi(String date) {
        String effectiveDate = StringUtils.hasText(date) ? date : "latest";
        String url = baseUrl + "/" + effectiveDate + "?access_key={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);

        if (!response.getStatusCode().is2xxSuccessful()) {
            String message = String.format("Can not get exchange rates. Details: %s" + response.getBody());
            throw new CanNotGetExchangeRatesException(message);
        }

        return response.getBody();
    }

}
