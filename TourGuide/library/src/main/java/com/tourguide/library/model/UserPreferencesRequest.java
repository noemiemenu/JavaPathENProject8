package com.tourguide.library.model;


/**
 * The type User preferences request.
 */
public class UserPreferencesRequest {
    private int attractionProximity = Integer.MAX_VALUE;
    private String currency = "USD";
    private int lowerPricePoint;
    private int highPricePoint;
    private int tripDuration = 1;
    private int ticketQuantity = 1;
    private int numberOfAdults = 1;
    private int numberOfChildren = 0;

    /**
     * Gets attraction proximity.
     *
     * @return the attraction proximity
     */
    public int getAttractionProximity() {
        return attractionProximity;
    }

    /**
     * Sets attraction proximity.
     *
     * @param attractionProximity the attraction proximity
     */
    public void setAttractionProximity(int attractionProximity) {
        this.attractionProximity = attractionProximity;
    }

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Gets lower price point.
     *
     * @return the lower price point
     */
    public int getLowerPricePoint() {
        return lowerPricePoint;
    }

    /**
     * Sets lower price point.
     *
     * @param lowerPricePoint the lower price point
     */
    public void setLowerPricePoint(int lowerPricePoint) {
        this.lowerPricePoint = lowerPricePoint;
    }

    /**
     * Gets high price point.
     *
     * @return the high price point
     */
    public int getHighPricePoint() {
        return highPricePoint;
    }

    /**
     * Sets high price point.
     *
     * @param highPricePoint the high price point
     */
    public void setHighPricePoint(int highPricePoint) {
        this.highPricePoint = highPricePoint;
    }

    /**
     * Gets trip duration.
     *
     * @return the trip duration
     */
    public int getTripDuration() {
        return tripDuration;
    }

    /**
     * Sets trip duration.
     *
     * @param tripDuration the trip duration
     */
    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    /**
     * Gets ticket quantity.
     *
     * @return the ticket quantity
     */
    public int getTicketQuantity() {
        return ticketQuantity;
    }

    /**
     * Sets ticket quantity.
     *
     * @param ticketQuantity the ticket quantity
     */
    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    /**
     * Gets number of adults.
     *
     * @return the number of adults
     */
    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    /**
     * Sets number of adults.
     *
     * @param numberOfAdults the number of adults
     */
    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    /**
     * Gets number of children.
     *
     * @return the number of children
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * Sets number of children.
     *
     * @param numberOfChildren the number of children
     */
    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
