package com.jpmc.theater;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int ticketCount;

    /** Constructor for Reservation class.
     * 
     * @param  customer         customer of the reservation
     * @param  showing          the showing of the reservation
     * @param  ticketCount      the amount of tickets for the reservation
     * @throws RuntimeException for an invalid ticket count
     */
    public Reservation(Customer customer, Showing showing, int ticketCount) {
        if(ticketCount <= 0)
            throw new RuntimeException("invalid ticket count, cannot be a non-positive number");
        this.customer = customer;
        this.showing = showing;
        this.ticketCount = ticketCount;
    }

    /** Gets the total fee for the reservation (after the discount is applied).
     * 
     * @return total fee
    */
    public double totalFee() {
        return showing.calculateFee() * this.ticketCount;
    }

    /** Gets the customer of the reservation.
     * 
     * @return customer of the reservation
    */
    public Customer getCustomer() {
        return this.customer;
    }

    /** Gets the showing of the reservation.
     *  
     * @return showing of the reservation
    */
    public Showing getShowing() {
        return this.showing;
    }

    /** Gets the amount of tickets in this reservation.
     * 
     * @return amount of tickets in this reservation
    */
    public int getTicketAmount(){
        return this.ticketCount;
    }
}