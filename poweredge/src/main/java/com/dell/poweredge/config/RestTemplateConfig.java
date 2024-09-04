package com.dell.poweredge.config;

import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class RestTemplateConfig {

    @Value("${corpaggregator.ssl.key-store}")
    private String keyStoreLocation;

    @Value("${corpaggregator.ssl.key-store-password}")
    private String keyStorePassword;

    @Value("${corpaggregator.ssl.key-store-type}")
    private String keyStoreType;

    @Value("${corpaggregator.ssl.trust-store}")
    private String trustStoreLocation;

    @Value("${corpaggregator.ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public RestTemplate restTemplate() throws Exception {
        // Load client keystore
        KeyStore clientKeyStore = KeyStore.getInstance(keyStoreType);
        try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keyStoreLocation)) {
            clientKeyStore.load(keyStoreStream, keyStorePassword.toCharArray());
        }

        // Load truststore
        KeyStore trustStore = KeyStore.getInstance(keyStoreType);
        try (InputStream trustStoreStream = getClass().getClassLoader().getResourceAsStream(trustStoreLocation)) {
            trustStore.load(trustStoreStream, trustStorePassword.toCharArray());
        }

        // Initialize KeyManagerFactory and TrustManagerFactory
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(clientKeyStore, keyStorePassword.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        // Initialize SSLContext
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(clientKeyStore, keyStorePassword.toCharArray())
                .loadTrustMaterial(trustStore, (TrustStrategy) null)
                .build();

        // Create SSLConnectionSocketFactory
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);

        // Create HttpClient with SSLConnectionSocketFactory
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .evictExpiredConnections()
                .build();

        ClientHttpRequestFactory factory = new HttpComponents5ClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }
}
