package com.shipmonk.testingday.fixerioprovider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipmonk.testingday.ExchangeRateCache;
import com.shipmonk.testingday.ExchangeRateProvider;
import com.shipmonk.testingday.model.ExchangeRates;
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
    public ExchangeRates getExchangeRates(final String date) {

        String payload;
        if (cache.isInCache(date)) {
            payload = cache.get(date).getPayload();
        } else {
            payload = getExchangeRatesFromApi(date);
            cache.upsert(date, payload);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ExchangeRates();
    }

    private String getExchangeRatesFromApi(String date) {
        String effectiveDate = StringUtils.hasText(date) ? date : "latest";
        String url = baseUrl + "/" + effectiveDate + "?access_key={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params);
        return response.getBody();
    }

}
