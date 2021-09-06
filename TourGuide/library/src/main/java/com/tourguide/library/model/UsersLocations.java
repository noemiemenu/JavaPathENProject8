package com.tourguide.library.model;

import gpsUtil.location.Location;

import java.util.UUID;

/**
 * The type Users locations.
 */
public class UsersLocations {
    private UUID userId;
    private Location location;

    /**
     * Instantiates a new Users locations.
     *
     * @param uuid     the uuid
     * @param location the location
     */
    public UsersLocations(UUID uuid, Location location) {
        this.userId = uuid;
        this.location = location;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId.toString();
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Location getLocation() {
        return location;
    }
}
