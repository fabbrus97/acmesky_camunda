package it.unibo.soseng.acmesky.Json;

import it.unibo.soseng.acmesky.Json.Price;

public class Flight{

    String departureFrom;
    String destination;
    String offerCode;
    Price price;
    String takeoff;

    public Flight() {
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(String currency, int amount) {
        Price price = new Price();
        price.setAmount(amount);
        price.setCurrency(currency);

        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }
}