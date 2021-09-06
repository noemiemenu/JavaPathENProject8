package com.tourguide.microservice.tourguide.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * The type Wire mock user api config.
 */
@TestConfiguration
public class WireMockUserAPIConfig {

    @Autowired
    private WireMockServer wireMockServer;

    /**
     * Wire mock server wire mock server.
     *
     * @return the wire mock server
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(9561);
    }
}
