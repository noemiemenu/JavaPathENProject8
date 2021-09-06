package com.tourguide.library.user;

import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The type User.
 */
public class User {
    private final UUID userId;
    private final String userName;
    private String phoneNumber;
    private String emailAddress;
    private Date latestLocationTimestamp = new Date();
    private final List<VisitedLocation> visitedLocations = new CopyOnWriteArrayList<>();
    private List<UserReward> userRewards;
    private UserPreferences userPreferences = new UserPreferences();
    private List<Provider> tripDeals = new ArrayList<>();

    /**
     * Instantiates a new User.
     *
     * @param userId       the user id
     * @param userName     the user name
     * @param phoneNumber  the phone number
     * @param emailAddress the email address
     */
    public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userRewards = new ArrayList<>();
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets latest location timestamp.
     *
     * @param latestLocationTimestamp the latest location timestamp
     */
    public void setLatestLocationTimestamp(Date latestLocationTimestamp) {
        this.latestLocationTimestamp = latestLocationTimestamp;
    }

    /**
     * Gets latest location timestamp.
     *
     * @return the latest location timestamp
     */
    public Date getLatestLocationTimestamp() {
        return latestLocationTimestamp;
    }

    /**
     * Add to visited locations.
     *
     * @param visitedLocation the visited location
     */
    public void addToVisitedLocations(VisitedLocation visitedLocation) {
        visitedLocations.add(visitedLocation);
    }

    /**
     * Gets visited locations.
     *
     * @return the visited locations
     */
    public List<VisitedLocation> getVisitedLocations() {
        return visitedLocations;
    }

    /**
     * Clear visited locations.
     */
    public void clearVisitedLocations() {
        visitedLocations.clear();
    }

    /**
     * Add user reward.
     *
     * @param userReward the user reward
     */
    public void addUserReward(UserReward userReward) {
        if (userRewards.stream().noneMatch(r -> r.attraction.attractionId == userReward.attraction.attractionId)) {
            userRewards.add(userReward);
        }
    }

    /**
     * Gets user rewards.
     *
     * @return the user rewards
     */
    public List<UserReward> getUserRewards() {
        return userRewards;
    }

    /**
     * Gets user preferences.
     *
     * @return the user preferences
     */
    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    /**
     * Sets user preferences.
     *
     * @param userPreferences the user preferences
     */
    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    /**
     * Gets last visited location.
     *
     * @return the last visited location
     */
    public VisitedLocation getLastVisitedLocation() {
        return visitedLocations.get(visitedLocations.size() - 1);
    }

    /**
     * Sets trip deals.
     *
     * @param tripDeals the trip deals
     */
    public void setTripDeals(List<Provider> tripDeals) {
        this.tripDeals = tripDeals;
    }

    /**
     * Gets trip deals.
     *
     * @return the trip deals
     */
    public List<Provider> getTripDeals() {
        return tripDeals;
    }

}
