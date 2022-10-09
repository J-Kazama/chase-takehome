package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    /** Constructor for the Showing class. Instatitates a showing based on the movie
     * sequence of the showing in the date, and showing's start time.
     * 
     * @param  movie            the movie
     * @param  sequenceOfTheDay the number of showing in the day
     * @param  showStartTime    the start time of the showing
     * @throws RuntimeException in case of an invalid movie, sequence, or start time
     */
    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        if(movie == null || sequenceOfTheDay <= 0 || showStartTime == null)
            throw new RuntimeException("A showing must have a movie, a start time, and a valid, positive sequence");
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    /** Showing's movie getter.
     * 
     * @return movie of this showing
     */
    public Movie getMovie() {
        return movie;
    }

    /** Showing's start time getter.
     * 
     * @return start time of this showing
     */
    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    /** Checks if the sequence provided is the sequence of the showing.
     * 
     * @param  sequence the sequence to match against
     * @return          true or false based on if the sequence provided matches the one of the showing
     */
    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    /** Showing's movie fee getter.
     * 
     * @return price of the ticket for the movie of this show
     */
    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    /** Showing's sequence getter.
     * 
     * @return the sequence of this showing
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    /** Calculates the price for this showing based on the movie and any possible discount.
     * 
     * @return the price of this showing.
     */
    double calculateFee() {
        return movie.calculateTicketPrice(this);
    }
}