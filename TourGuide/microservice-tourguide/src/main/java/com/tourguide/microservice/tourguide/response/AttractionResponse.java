package com.tourguide.microservice.tourguide.response;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;


public class AttractionResponse extends Attraction {

    private Location userLocation;
    private int rewardPoints;
    private double distanceInMiles;


    public AttractionResponse(Attraction attraction, double distanceInMiles, Location userLocation, int rewardPoints) {
        super(attraction.attractionName, attraction.city, attraction.state, attraction.latitude, attraction.longitude);
        this.distanceInMiles = distanceInMiles;
        this.rewardPoints = rewardPoints;
        this.userLocation = userLocation;
    }
}
