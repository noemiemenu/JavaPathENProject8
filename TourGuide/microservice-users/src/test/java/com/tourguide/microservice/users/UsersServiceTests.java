package com.tourguide.microservice.users;

import com.tourguide.library.model.UsersLocations;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import com.tourguide.microservice.users.services.UserService;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tripPricer.Provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceTests {


    @Autowired
    private UserService userService;



    @Test
    void getUserTest() {
        User user1 = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        User user2 = new User(UUID.randomUUID(), "jon2", "000", "jon2@tourGuide.com");

        userService.addUser(user1);
        userService.addUser(user2);

        List<User> allUsers = userService.getAllUsers();


        assertTrue(allUsers.contains(user1));
        assertTrue(allUsers.contains(user2));
        assertNotNull(user1);
        assertNotNull(user1.getUserId());
    }

    @Test
    void getAllUsersTest(){
        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertNotEquals(0, users.size());
    }

    @Test
    void addUserRewards(){
        User user = userService.getAllUsers().get(0);
        long now = new Date().getTime();
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1234567, 1234567), new Date());
        Attraction attraction = new Attraction("name", "city", "stat", 1234567, 12345678);
        UserReward userReward = new UserReward(visitedLocation, attraction, 325);
        userService.addUserReward(user.getUserName(), userReward);

        assertNotNull(user.getUserRewards());
        assertNotEquals(0, user.getUserRewards().size());
    }

    @Test
    void getUserRewardsTest(){
        User user = userService.getAllUsers().get(0);
        long now = new Date().getTime();
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1234567, 1234567), new Date());
        Attraction attraction = new Attraction("name", "city", "stat", 1234567, 12345678);
        UserReward userReward = new UserReward(visitedLocation, attraction, 325);
        user.addUserReward(userReward);
        List<UserReward> userRewards = userService.getUserRewards(user);

        assertNotNull(userRewards);
        assertNotEquals(0, userRewards.size());
        assertNotNull(userRewards.get(0).attraction.attractionId);
    }

    @Test
    void getAllCurrentLocationsTest(){
        List<UsersLocations> usersLocations = userService.getAllCurrentLocations();
        assertNotNull(usersLocations);
        assertNotEquals(0, usersLocations.size());
        assertNotNull(usersLocations.get(0).getUserId());
    }

    @Test
    void updateTripDealsTest(){
        User user = userService.getAllUsers().get(0);
        Provider provider = new Provider(UUID.randomUUID(), "name", 200);
        Provider provider2 = new Provider(UUID.randomUUID(), "nameTest", 300);
        List<Provider> providerList = new ArrayList<>();
        providerList.add(0,provider);
        providerList.add(1,provider2);

        userService.updateTripDeals(user.getUserName(), providerList);

        assertNotNull(user.getTripDeals());
        assertNotEquals(0, userService.getUser(user.getUserName()).getTripDeals().size());
        assertNotNull(user.getTripDeals().get(0).tripId);

    }

    @Test
    void createVisitedLocationTest(){
        //given
        User user = userService.getAllUsers().get(0);
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1234567, 1234567), new Date());
        int visitedLocationNumber = user.getVisitedLocations().size();

        //when
        userService.addVisitedLocation(user.getUserName(),visitedLocation);
        //then
        assertEquals(visitedLocationNumber + 1, userService.getAllUsers().get(0).getVisitedLocations().size());
    }

}
