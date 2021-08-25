package com.tourguide.microservice.tourguide.service;

import com.tourguide.feign_clients.RewardsAPI;
import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.model.DistancesHolder;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserReward;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tourguide.library.model.DistanceOfAttraction;
import com.tourguide.microservice.tourguide.response.AttractionResponse;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TourGuideService {
    private final GpsUtil gpsUtil;
    private final TripPricer tripPricer = new TripPricer();
    private final ExecutorService executorService = Executors.newFixedThreadPool(64);

    @Autowired
    private RewardsAPI rewardsAPI;

    @Autowired
    private UsersAPI usersAPI;

    public DistanceOfAttraction distanceOfAttraction;

    public TourGuideService(GpsUtil gpsUtil) {
        this.gpsUtil = gpsUtil;
    }

    public VisitedLocation getUserLocation(User user) {
        return user.getLastVisitedLocation();
    }

    public List<Provider> getTripDeals(User user) {
        int cumulatedRewardPoints = user.getUserRewards().stream().mapToInt(UserReward::getRewardPoints).sum();
        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey, user.getUserId(), user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(), user.getUserPreferences().getTripDuration(), cumulatedRewardPoints);
        usersAPI.updateTripDeals(user.getUserName(), providers);
        return providers;
    }

    public void trackUserLocation(User user) {
        CompletableFuture
                .supplyAsync(() -> gpsUtil.getUserLocation(user.getUserId()), executorService)
                .thenAccept((_visitedLocation) -> {
                    usersAPI.createVisitedLocation(user.getUserName(), _visitedLocation);
                    user.addToVisitedLocations(_visitedLocation);
                    rewardsAPI.calculateRewards(user.getUserName());
                });
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public List<AttractionResponse> getNearByAttractions(VisitedLocation visitedLocation, User user) {
        List<AttractionResponse> nearbyAttractions = new ArrayList<>();
        List<DistanceOfAttraction> distances = new ArrayList<>();

        for (Attraction attraction : gpsUtil.getAttractions()) {
            DistancesHolder distancesHolder = new DistancesHolder(visitedLocation.location, attraction);
            distances.add(new DistanceOfAttraction(rewardsAPI.getDistance(distancesHolder), attraction));
        }

        distances.sort((distance1, distance2) -> {
            if (distance1.getDistance() > distance2.getDistance()) return -1;
            if (distance1.getDistance() < distance2.getDistance()) return 1;
            return 0;
        });

        List<DistanceOfAttraction> fiveFirstAttractions = distances.subList(0, 5);

        for (DistanceOfAttraction distanceOfAttraction : fiveFirstAttractions) {
            int reward = rewardsAPI.getRewardPoints(distanceOfAttraction.getAttraction(), user.getUserName());
            double distance = distanceOfAttraction.getDistance();
            nearbyAttractions.add(new AttractionResponse(distanceOfAttraction.getAttraction(), distance, visitedLocation.location, reward));
        }

        return nearbyAttractions;
    }

    private static final String tripPricerApiKey = "test-server-api-key";
}
