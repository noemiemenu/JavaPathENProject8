package com.tourguide.microservice.tourguide.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class ServerAPIMocks {

    public static void setupMockUserAPIResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(
                WireMock.get(
                        WireMock.urlEqualTo("/user/internalUser60"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUser.json"),
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
                WireMock.post(
                        WireMock.urlEqualTo("/tripDeals/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        )

        );

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/rewards/internalUser60"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUserReward.json"),
                                        defaultCharset()))));

        //RewardsAPI
        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/getRewardPoints/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-getRewardsPoints.json"),
                                                        defaultCharset()
                                                )
                                        )
                        )
        );
        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/getDistance"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-getDistance.json"),
                                                        defaultCharset()
                                                )
                                        )
                        )
        );

        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/calculateRewards/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-calculateRewards.json"),
                                                        defaultCharset()
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


    public static void setupMockUserAPICalculateResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/calculateRewards/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }
    public static void setupMockUserAPICreateVisitedLocationResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/addVisitedLocation/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }


}
