package com.tourguide.microservice.tourguide;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.user.User;
import com.tourguide.microservice.tourguide.config.ServerAPIMocks;
import com.tourguide.microservice.tourguide.config.WireMockUserAPIConfig;
import com.tourguide.microservice.tourguide.response.AttractionResponse;
import com.tourguide.microservice.tourguide.service.TourGuideService;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tripPricer.Provider;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Tourguide service tests.
 */
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockUserAPIConfig.class })
class TourguideServiceTests {

    @Autowired
    private TourGuideService tourGuideService;

    @Autowired
    private WireMockServer mockService;

    @Autowired
    private UsersAPI usersAPI;


    /**
     * Sets up.
     *
     * @throws IOException the io exception
     */
    @BeforeEach
    void setUp() throws IOException {
        ServerAPIMocks.setupMockUserAPIResponse(mockService);
    }


    /**
     * Gets user location test.
     */
    @Test
    void getUserLocationTest() {
        User user = usersAPI.getUser("internalUser60");
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);

        assertNotNull(visitedLocation);
        assertNotNull(user.getVisitedLocations());
        assertNotNull(visitedLocation.location);
    }

    /**
     * Get trip deals test.
     */
    @Test
    void getTripDealsTest(){
        User user = usersAPI.getUser("internalUser60");

        List<Provider> providerList = tourGuideService.getTripDeals(user);

        assertNotNull(providerList.get(0));
        assertNotNull(providerList);
        assertNotEquals(0, providerList.size());
        assertNotNull(user.getTripDeals());
    }

    /**
     * Get near by attractions test.
     */
    @Test
    void getNearByAttractionsTest(){
        User user = usersAPI.getUser("internalUser60");

        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1234567, 1234567), new Date());
        List<AttractionResponse> attractionResponses = tourGuideService.getNearByAttractions(visitedLocation, user);

        assertNotNull(attractionResponses);
        assertNotNull(attractionResponses.get(0));
        assertNotNull(user.getVisitedLocations());
    }

    /**
     * Track user location test.
     */
    @Test
    void trackUserLocationTest(){
        Locale.setDefault(new Locale("en", "US"));
        User user = usersAPI.getUser("internalUser60");

        tourGuideService.trackUserLocation(user);

        VisitedLocation trackVisitedLocation = user.getLastVisitedLocation();

        assertNotNull(trackVisitedLocation);
        assertNotNull(trackVisitedLocation.location);
        assertNotNull(trackVisitedLocation.userId);
        assertNotNull(trackVisitedLocation.timeVisited);
    }


}
