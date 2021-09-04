package com.tourguide.microservice.rewards;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tourguide.library.helper.InternalTestHelper;
import com.tourguide.library.helper.UsersHelper;
import com.tourguide.library.user.User;
import com.tourguide.microservice.rewards.config.UserAPIMocks;
import com.tourguide.microservice.rewards.config.WireMockConfig;
import com.tourguide.microservice.rewards.service.RewardsService;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rewardCentral.RewardCentral;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class TestPerformance {

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private WireMockServer mockService;

    @Autowired
    private GpsUtil gpsUtil;

    private final Map<String, User> internalUserMap = new HashMap<>();

    // Users should be incremented up to 100,000, and test finishes within 20 minutes
    private static final int internalUserNumber = 100;


    @BeforeEach
    void setUp() throws IOException {
        UserAPIMocks.setupMockUserAPIResponse(mockService);
        UserAPIMocks.setupMockUserAPIRewardResponses(mockService, internalUserNumber);
        initializeInternalUsers();
    }

    private void initializeInternalUsers() {
        IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            UsersHelper.generateUserLocationHistory(user);

            internalUserMap.put(userName, user);
        });
    }

    @Test
    public void highVolumeGetRewards() {
        Locale.setDefault(new Locale("en", "US"));
        InternalTestHelper.setInternalUserNumber(internalUserNumber);
        StopWatch stopWatch = new StopWatch();
        rewardsService.resetThreadPool();

        Attraction attraction = gpsUtil.getAttractions().get(0);
        List<User> allUsers = new ArrayList<>(internalUserMap.values());
        allUsers.forEach(u -> u.addToVisitedLocations(new VisitedLocation(u.getUserId(), attraction, new Date())));

        stopWatch.start();
        allUsers.forEach(rewardsService::calculateRewards);

        ExecutorService executorService = rewardsService.getExecutorService();

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(15, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(User user : allUsers) {
            assertTrue(user.getUserRewards().size() > 0);
        }
        stopWatch.stop();

        System.out.println("highVolumeGetRewards: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}
