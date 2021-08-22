package com.tourguide.microservice.tourguide;

import com.tourguide.library.helper.UsersHelper;
import com.tourguide.library.user.User;
import com.tourguide.microservice.tourguide.response.AttractionResponse;
import com.tourguide.microservice.tourguide.service.TourGuideService;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tripPricer.Provider;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourguideServiceTests {

    @Autowired
    private TourGuideService tourGuideService;

    private User user;

    @BeforeEach
    public void setup() {
        String userName = "internalUser1";
        String phone = "000";
        String email = userName + "@tourGuide.com";
        this.user = new User(UUID.randomUUID(), userName, phone, email);
        UsersHelper.generateUserLocationHistory(this.user);
    }

    @Test
    void getUserLocationTest() {
        VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);

        assertNotNull(visitedLocation);
        assertNotNull(user.getVisitedLocations());
        assertNotNull(visitedLocation.location);
    }

    @Test
    void getTripDealsTest(){
        List<Provider> providerList = tourGuideService.getTripDeals(user);

        assertNotNull(providerList.get(0));
        assertNotNull(providerList);
        assertNotEquals(0, providerList.size());
        assertNotNull(user.getTripDeals());
    }

    @Test
    void getNearByAttractionsTest(){
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1234567, 1234567), new Date());
        List<AttractionResponse> attractionResponses = tourGuideService.getNearByAttractions(visitedLocation, user);

        assertNotNull(attractionResponses);
        assertNotNull(attractionResponses.get(0));
        assertNotNull(user.getVisitedLocations());
    }

    @Test
    void trackUserLocationTest(){
        Locale.setDefault(new Locale("en", "US"));
        VisitedLocation trackVisitedLocation = tourGuideService.trackUserLocation(user);

        assertNotNull(trackVisitedLocation);
        assertNotNull(trackVisitedLocation.location);
        assertNotNull(trackVisitedLocation.userId);
        assertNotNull(trackVisitedLocation.timeVisited);
    }


}
