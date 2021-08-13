package com.tourguide.library.model;

import gpsUtil.location.Attraction;


public class DistanceOfAttraction{
    private final double distance;
    private final Attraction attraction;


    public DistanceOfAttraction(double distance, Attraction attraction) {
        this.distance = distance;
        this.attraction = attraction;
    }

    public double getDistance() {
        return distance;
    }

    public Attraction getAttraction() {
        return attraction;
    }
}
