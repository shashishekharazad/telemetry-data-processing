package com.dell.poweredge.service;

import com.dell.poweredge.model.TelemetryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class PoweredgeService {
    private final RestTemplate restTemplate;
    private final Random random = new Random();

    @Value("${corpaggregator.url}")
    private String corpAggregatorUrl;

    @Value("${corpaggregator.username}")
    private String username;

    @Value("${corpaggregator.password}")
    private String password;

    public PoweredgeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 30000)
    public void sendTelemetryData() {
        TelemetryData data = generateRandomTelemetryData();
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        HttpEntity<TelemetryData> request = new HttpEntity<>(data, headers);
        restTemplate.exchange(corpAggregatorUrl, HttpMethod.POST, request, String.class);
    }

    public TelemetryData generateRandomTelemetryData() {
        TelemetryData data = new TelemetryData();
        data.setTemperature(20 + random.nextDouble() * 10);
        data.setNetworkSpeed(100 + random.nextDouble() * 50);
        data.setDiskUtilization(random.nextDouble() * 100);
        data.setCpuUtilization(random.nextDouble() * 100);
        data.setTimestamp(LocalDateTime.now());
        return data;
    }
}
