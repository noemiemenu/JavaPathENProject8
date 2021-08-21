package com.tourguide.library.user;
import org.javamoney.moneta.Money;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.io.Serializable;
import java.math.BigDecimal;


public class UserPreferences {

    private int attractionProximity = Integer.MAX_VALUE;
    private CurrencyUnit currency = Monetary.getCurrency("USD");

    private Money lowerPricePoint = Money.of(0, currency);
    private Money highPricePoint = Money.of(Integer.MAX_VALUE, currency);

    private int tripDuration = 1;
    private int ticketQuantity = 1;
    private int numberOfAdults = 1;
    private int numberOfChildren = 0;

    public UserPreferences() { }

    public void setAttractionProximity(int attractionProximity) {
        this.attractionProximity = attractionProximity;
    }

    public int getAttractionProximity() {
        return attractionProximity;
    }

    public Money getLowerPricePoint() {
        return lowerPricePoint;
    }

    public void setLowerPricePoint(Money lowerPricePoint) {
        this.lowerPricePoint = lowerPricePoint;
    }
    public void setLowerPricePoint(String lowerPricePoint) {
        this.lowerPricePoint = Money.of(Integer.parseInt(lowerPricePoint), currency);
    }

    public void setLowerPricePoint(int lowerPricePoint) {
        this.lowerPricePoint = Money.of(lowerPricePoint, currency);
    }

	public Money getHighPricePoint() {
		return highPricePoint;
	}

    public void setHighPricePoint(Money highPricePoint) {
        this.highPricePoint = highPricePoint;
    }

    public void setHighPricePoint(String highPricePoint) {
        this.highPricePoint = Money.of(Integer.parseInt(highPricePoint), currency);
    }

    public void setHighPricePoint(int highPricePoint) {
        this.highPricePoint = Money.of(highPricePoint, currency);
    }

    public int getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getCurrency() {
        return currency.getCurrencyCode();
    }

    public void setCurrency(String currency){
        this.currency = Monetary.getCurrency(currency);
    }
}
