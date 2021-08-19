package com.tourguide.microservice.users.services;

import com.tourguide.library.helper.InternalTestHelper;
import com.tourguide.library.helper.UsersHelper;
import com.tourguide.library.model.UsersLocations;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tripPricer.Provider;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    boolean testMode = true;
    private final Map<String, User> internalUserMap = new HashMap<>();

    public UserService(){

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
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
        logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
    }

    public User getUser(String userName) {
        return internalUserMap.get(userName);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(internalUserMap.values());
    }


    public void addUser(User user) {
        if (!internalUserMap.containsKey(user.getUserName())) {
            internalUserMap.put(user.getUserName(), user);
        }
    }

    public List<UserReward> getUserRewards(User user) {
        return user.getUserRewards();
    }

    public void addUserReward(String userName, UserReward userReward) {
        getUser(userName).addUserReward(userReward);
    }


    public List<UsersLocations> getAllCurrentLocations() {
        List<UsersLocations> usersLocationsList = new ArrayList<>();

        for (User user : getAllUsers()) {
            UUID userId = user.getUserId();
            VisitedLocation userLastVisitedLocation = user.getLastVisitedLocation();
            usersLocationsList.add(new UsersLocations(userId, userLastVisitedLocation.location));
        }
        return usersLocationsList;
    }

    public void updateTripDeals(String userName, List<Provider> tripDeals) {
        User user = getUser(userName);
        user.setTripDeals(tripDeals);
    }
}
