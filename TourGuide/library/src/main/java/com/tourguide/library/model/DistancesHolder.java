package com.tourguide.library.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;

/**
 * The type Distances holder.
 */
public class DistancesHolder {

    private Location location1;
    private Location location2;

    /**
     * Instantiates a new Distances holder.
     *
     * @param location   the location
     * @param attraction the attraction
     */
    public DistancesHolder(Location location, Location attraction) {
        this.location1 = location;
        this.location2 = attraction;
    }

    /**
     * Gets location 1.
     *
     * @return the location 1
     */
    public Location getLocation1() {
        return location1;
    }

    /**
     * Sets location 1.
     *
     * @param location1 the location 1
     */
    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    /**
     * Gets location 2.
     *
     * @return the location 2
     */
    public Location getLocation2() {
        return location2;
    }

    /**
     * Sets location 2.
     *
     * @param location2 the location 2
     */
    public void setLocation2(Attraction location2) {
        this.location2 = location2;
    }
}
