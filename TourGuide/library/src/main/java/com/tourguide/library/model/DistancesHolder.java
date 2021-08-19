package com.tourguide.library.model;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;

public class DistancesHolder {

    private Location location1;
    private Location location2;

    public DistancesHolder(Location location, Location attraction) {
        this.location1 = location;
        this.location2 = attraction;
    }

    public Location getLocation1() {
        return location1;
    }

    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public void setLocation2(Attraction location2) {
        this.location2 = location2;
    }
}
