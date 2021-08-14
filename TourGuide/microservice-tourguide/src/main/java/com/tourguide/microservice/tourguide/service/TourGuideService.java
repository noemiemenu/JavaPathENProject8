package com.tourguide.microservice.tourguide.service;

import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.tourguide.library.helper.InternalTestHelper;
import com.tourguide.library.model.DistanceOfAttraction;
import com.tourguide.library.model.UsersLocations;
import com.tourguide.microservice.tourguide.response.AttractionResponse;
import com.tourguide.microservice.tourguide.tracker.Tracker;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class TourGuideService {
    private Logger logger = LoggerFactory.getLogger(TourGuideService.class);
    private final GpsUtil gpsUtil;
    private final TripPricer tripPricer = new TripPricer();
    public final Tracker tracker;
    boolean testMode = true;

    public DistanceOfAttraction distanceOfAttraction;

    public TourGuideService(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;

        if (testMode) {
            logger.info("TestMode enabled");
            logger.debug("Initializing users");
            initializeInternalUsers();
            logger.debug("Finished initializing users");
        }
        tracker = new Tracker(this);

        addShutDownHook();
    }

    public VisitedLocation getUserLocation(User user) {
        // call to users microservice

        return (user.getVisitedLocations().size() > 0) ?
                user.getLastVisitedLocation() :
                trackUserLocation(user);
    }

    public List<Provider> getTripDeals(User user) {
        int cumulatedRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatedRewardPoints);
        user.setTripDeals(providers);
        return providers;
    }

    public VisitedLocation trackUserLocation(User user) {
        VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
        user.addToVisitedLocations(visitedLocation);
        // Todo :call to rewards microservice
        rewardsService.calculateRewards(user);
        return visitedLocation;
    }

    public List<AttractionResponse> getNearByAttractions(VisitedLocation visitedLocation, User user) {
        List<AttractionResponse> nearbyAttractions = new ArrayList<>();
        List<DistanceOfAttraction> distances = new ArrayList<>();

        for (Attraction attraction : gpsUtil.getAttractions()) {
            distances.add(new DistanceOfAttraction(rewardsService.getDistance(visitedLocation.location, attraction), attraction));
        }

        distances.sort((distance1, distance2) -> {
            if (distance1.getDistance() > distance2.getDistance()) return -1;
            if (distance1.getDistance() < distance2.getDistance()) return 1;
            return 0;
        });

        List<DistanceOfAttraction> fiveFirstAttractions = distances.subList(0, 5);

        for (DistanceOfAttraction distanceOfAttraction : fiveFirstAttractions) {
            int reward = rewardsService.getRewardPoints(distanceOfAttraction.getAttraction(), user);
            double distance = distanceOfAttraction.getDistance();
            nearbyAttractions.add(new AttractionResponse(distanceOfAttraction.getAttraction(), distance, visitedLocation.location, reward));
        }

        return nearbyAttractions;
    }

    private void addShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                tracker.stopTracking();
            }
        });
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


    /**********************************************************************************
     *
     * Methods Below: For Internal Testing
     *
     **********************************************************************************/
    private static final String tripPricerApiKey = "test-server-api-key";
    // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
    private final Map<String, User> internalUserMap = new HashMap<>();

    private void initializeInternalUsers() {
        IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);

            internalUserMap.put(userName, user);
        });
        logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
    }

    private void generateUserLocationHistory(User user) {
        IntStream.range(0, 3).forEach(i -> {
            user.addToVisitedLocations(new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
        });
    }

    private double generateRandomLongitude() {
        double leftLimit = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private double generateRandomLatitude() {
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private Date getRandomTime() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

}
