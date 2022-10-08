package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;

    public Reservation(Customer customer, Showing showing) {
        this.customer = customer;
        this.showing = showing;
    }

    public double totalFee() {
        return showing.getMovieFee();
    }
}