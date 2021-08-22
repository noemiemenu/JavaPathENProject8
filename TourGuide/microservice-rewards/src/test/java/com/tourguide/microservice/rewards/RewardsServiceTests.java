package com.tourguide.microservice.rewards;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.rewards.config.UserAPIMocks;
import com.tourguide.microservice.rewards.config.WireMockConfig;
import com.tourguide.microservice.rewards.service.RewardsService;
import gpsUtil.GpsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class RewardsServiceTests {

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private WireMockServer mockService;

    @Autowired
    private GpsUtil gpsUtil;

    @BeforeEach
    void setUp() throws IOException {
        UserAPIMocks.setupMockUserAPIResponse(mockService);
    }

    @Test
    public void calculateRewardsTest() {
        Locale.setDefault(new Locale("en", "US"));
        rewardsService.setProximityBuffer(Integer.MAX_VALUE);

        List<UserReward> userRewards = rewardsService.calculateRewards("internalUser60");

        assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
    }

}
