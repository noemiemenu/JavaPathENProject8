package com.tourguide.library.user;
import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The type User preferences.
 */
public class UserPreferences {

    private int attractionProximity = Integer.MAX_VALUE;
    private CurrencyUnit currency = Monetary.getCurrency("USD");

    private Money lowerPricePoint = Money.of(0, currency);
    private Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);

    private int tripDuration = 1;
    private int ticketQuantity = 1;
    private int numberOfAdults = 1;
    private int numberOfChildren = 0;

    /**
     * Instantiates a new User preferences.
     */
    public UserPreferences() { }

    /**
     * Sets attraction proximity.
     *
     * @param attractionProximity the attraction proximity
     */
    public void setAttractionProximity(int attractionProximity) {
        this.attractionProximity = attractionProximity;
    }

    /**
     * Gets attraction proximity.
     *
     * @return the attraction proximity
     */
    public int getAttractionProximity() {
        return attractionProximity;
    }

    /**
     * Gets lower price point.
     *
     * @return the lower price point
     */
    public Money getLowerPricePoint() {
        return lowerPricePoint;
    }

    /**
     * Sets lower price point.
     *
     * @param lowerPricePoint the lower price point
     */
    public void setLowerPricePoint(Money lowerPricePoint) {
        this.lowerPricePoint = lowerPricePoint;
    }

    /**
     * Sets lower price point.
     *
     * @param lowerPricePoint the lower price point
     */
    public void setLowerPricePoint(String lowerPricePoint) {
        this.lowerPricePoint = Money.of(Integer.parseInt(lowerPricePoint), currency);
    }

    /**
     * Sets lower price point.
     *
     * @param lowerPricePoint the lower price point
     */
    public void setLowerPricePoint(int lowerPricePoint) {
        this.lowerPricePoint = Money.of(lowerPricePoint, currency);
    }

    /**
     * Gets high price point.
     *
     * @return the high price point
     */
    public Money getHighPricePoint() {
		return highPricePoint;
	}

    /**
     * Sets high price point.
     *
     * @param highPricePoint the high price point
     */
    public void setHighPricePoint(Money highPricePoint) {
        this.highPricePoint = highPricePoint;
    }

    /**
     * Sets high price point.
     *
     * @param highPricePoint the high price point
     */
    public void setHighPricePoint(String highPricePoint) {
        this.highPricePoint = Money.of(Integer.parseInt(highPricePoint), currency);
    }

    /**
     * Sets high price point.
     *
     * @param highPricePoint the high price point
     */
    public void setHighPricePoint(int highPricePoint) {
        this.highPricePoint = Money.of(highPricePoint, currency);
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

    /**
     * Gets currency.
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency.getCurrencyCode();
    }

    /**
     * Set currency.
     *
     * @param currency the currency
     */
    public void setCurrency(String currency){
        this.currency = Monetary.getCurrency(currency);
    }
}
