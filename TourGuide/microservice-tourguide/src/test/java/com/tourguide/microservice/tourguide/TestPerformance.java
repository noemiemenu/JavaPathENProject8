package com.tourguide.microservice.tourguide;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tourguide.library.helper.InternalTestHelper;
import com.tourguide.library.helper.UsersHelper;
import com.tourguide.library.user.User;
import com.tourguide.microservice.tourguide.config.ServerAPIMocks;
import com.tourguide.microservice.tourguide.config.WireMockUserAPIConfig;
import com.tourguide.microservice.tourguide.service.TourGuideService;
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

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Test performance.
 */
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockUserAPIConfig.class })
public class TestPerformance {

    @Autowired
    private WireMockServer mockService;

    @Autowired
    private TourGuideService tourGuideService;


    private final Map<String, User> internalUserMap = new HashMap<>();


    // Users should be incremented up to 100,000, and test finishes within 15 minutes
    private static final int internalUserNumber = 100;


    /**
     * Sets up.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setUp() throws IOException {
        ServerAPIMocks.setupMockUserAPICalculateResponses(mockService, internalUserNumber);
        ServerAPIMocks.setupMockUserAPIRewardResponses(mockService, internalUserNumber);
        ServerAPIMocks.setupMockUserAPICreateVisitedLocationResponses(mockService, internalUserNumber);
        InternalTestHelper.setInternalUserNumber(internalUserNumber);
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

    /**
     * High volume track location.
     */
    @Test
    public void highVolumeTrackLocation() {
        Locale.setDefault(new Locale("en", "US"));
        List<User> allUsers = new ArrayList<>(internalUserMap.values());
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        for(User user : allUsers) {
            tourGuideService.trackUserLocation(user);
        }

        ExecutorService executorService = tourGuideService.getExecutorService();

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(15, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();

        System.out.println("highVolumeTrackLocation: Time Elapsed: " + TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()) + " seconds.");
        assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
    }
}
