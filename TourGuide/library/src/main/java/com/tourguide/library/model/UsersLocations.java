package com.tourguide.library.model;

import gpsUtil.location.Location;

import java.util.UUID;

public class UsersLocations {
    private UUID userId;
    private Location location;

    public UsersLocations(UUID uuid, Location location) {
        this.userId = uuid;
        this.location = location;
    }

    public String getUserId() {
        return userId.toString();
    }

    public Location getLocation() {
        return location;
    }
}
