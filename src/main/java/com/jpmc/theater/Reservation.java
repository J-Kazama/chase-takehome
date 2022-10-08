package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int ticketCount;

    public Reservation(Customer customer, Showing showing, int ticketCount) {
        this.customer = customer;
        this.showing = showing;
        this.ticketCount = ticketCount;
    }

    public double totalFee() {
        return showing.calculateFee() * this.ticketCount;
    }

    public Customer getCustomer() {
        return this.customer;
    }
}