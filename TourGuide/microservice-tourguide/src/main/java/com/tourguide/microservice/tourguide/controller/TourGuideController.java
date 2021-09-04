package com.tourguide.microservice.tourguide.controller;

import javax.validation.Valid;
import com.tourguide.feign_clients.UsersAPI;
import com.tourguide.library.model.UserPreferencesRequest;
import com.tourguide.library.user.User;
import com.tourguide.library.user.UserPreferences;
import com.tourguide.microservice.tourguide.response.AttractionResponse;
import com.tourguide.microservice.tourguide.service.TourGuideService;
import gpsUtil.location.VisitedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;


import java.util.List;

@RestController
public class TourGuideController {

	@Autowired
    private TourGuideService tourGuideService;

	@Autowired
    private UsersAPI usersAPI;

    /**
     * @return string "Greetings from TourGuide!"
     */
    @GetMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }

    /**
     * @param userName
     * @return the location of this user
     */
    @GetMapping("/getLocation/{userName}")
    public VisitedLocation getLocation(@PathVariable String userName) {
    	return tourGuideService.getUserLocation(usersAPI.getUser(userName));
    }

    /**
     * @param userName
     * @return the first 5 attraction of this user
     */
    @GetMapping("/getNearbyAttractions/{userName}")
    public List<AttractionResponse> getNearbyAttractions(@PathVariable String userName) {
        User user = usersAPI.getUser(userName);
    	VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
    	return tourGuideService.getNearByAttractions(visitedLocation, user);
    }

    /**
     * @param userName
     * @param userPreferencesRequest
     * @return a list of TripDeals based on user preferences
     */
    @GetMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName, @Valid UserPreferencesRequest userPreferencesRequest) {
        User user = usersAPI.getUser(userName);
        UserPreferences userPreferences = user.getUserPreferences();
        userPreferences.setCurrency(userPreferencesRequest.getCurrency());
        userPreferences.setAttractionProximity(userPreferencesRequest.getAttractionProximity());
        userPreferences.setHighPricePoint(userPreferencesRequest.getHighPricePoint());
        userPreferences.setLowerPricePoint(userPreferencesRequest.getLowerPricePoint());
        userPreferences.setTripDuration(userPreferencesRequest.getTripDuration());
        userPreferences.setTicketQuantity(userPreferencesRequest.getTicketQuantity());
        userPreferences.setNumberOfAdults(userPreferencesRequest.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesRequest.getNumberOfChildren());
        return tourGuideService.getTripDeals(user);
    }

    /**
     * @param userName
     * track the location of this user
     */
    @GetMapping("/trackUserLocation/{userName}")
    public void trackUserLocation(@PathVariable String userName){
        User user = usersAPI.getUser(userName);
        tourGuideService.trackUserLocation(user);
    }

}