package com.tourguide.library.model;


import gpsUtil.location.Attraction;

/**
 * The type Distance of attraction.
 */
public class DistanceOfAttraction {
    private final double distance;
    private final Attraction attraction;


    /**
     * Instantiates a new Distance of attraction.
     *
     * @param distance   the distance
     * @param attraction the attraction
     */
    public DistanceOfAttraction(double distance, Attraction attraction) {
        this.distance = distance;
        this.attraction = attraction;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Gets attraction.
     *
     * @return the attraction
     */
    public Attraction getAttraction() {
        return attraction;
    }
}
