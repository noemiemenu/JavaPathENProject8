package com.tourguide.microservice.rewards;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.rewards.config.UserAPIMocks;
import com.tourguide.microservice.rewards.config.WireMockConfig;
import com.tourguide.microservice.rewards.service.RewardsService;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;


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
    private UsersAPI usersAPI;

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
        assertNotNull(userRewards.get(0));
        assertNotNull(userRewards.get(0).attraction.attractionId);
    }

    @Test
    public void getRewardPointsTest(){
        int rewardPoints = rewardsService.getRewardPoints(
                new Attraction("name","city","state", 112345678, 123456789),
                usersAPI.getUser("internalUser60")
        );

        assertNotEquals(0, rewardPoints);
    }

    @Test
    public void isWithinAttractionProximity() {
        Locale.setDefault(new Locale("en", "US"));
        GpsUtil gpsUtil = new GpsUtil();
        Attraction attraction = gpsUtil.getAttractions().get(0);
        assertTrue(rewardsService.isWithinAttractionProximity(attraction, attraction));
    }


}
