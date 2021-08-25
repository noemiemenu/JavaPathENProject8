package com.tourguide.microservice.rewards.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class UserAPIMocks {

    public static void setupMockUserAPIResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(
                WireMock.get(
                        WireMock.urlEqualTo("/user/internalUser60"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                UserAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUser.json"),
                                                defaultCharset()
                                        )
                                ))
        );

        mockService.stubFor(
                WireMock.post(
                        WireMock.urlEqualTo("/rewards/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        )

        );

        mockService.stubFor(
                        WireMock.get(WireMock.urlEqualTo("/rewards/internalUser60")
                        ).willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(UserAPIMocks.class.getClassLoader()
                                                .getResourceAsStream("responses/mocks-UsersAPI-getUserReward.json"), defaultCharset()
                                        )
                                )
                        )
        );
    }

    public static void setupMockUserAPIRewardResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/rewards/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }

}
