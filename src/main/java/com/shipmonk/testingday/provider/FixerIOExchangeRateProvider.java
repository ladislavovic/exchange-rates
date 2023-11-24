package com.shipmonk.testingday.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Value("${fixerio.base-url}")
    private String baseUrl;

    @Value("${fixerio.apikey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ExchangeRates getExchangeRates(final String date) {

        // call rest; parse response to the object; create response data

        String effectiveDate = StringUtils.hasText(date) ? date : "latest";
        String url = baseUrl + "/" + effectiveDate + "?access_key={apiKey}";
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, params); // TODO optimise

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String body = response.getBody();
            System.out.println("body:" + body);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
