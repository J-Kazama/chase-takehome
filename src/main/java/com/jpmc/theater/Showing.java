package com.jpmc.theater;

import java.time.LocalDateTime;

/** A showing class for JPMorgan Chase's take home assignment. The showing class handles the streaming of 
 * a movie at the theater. The showing stores information such the screening time, sequence of it within the day
 * and calculates discount associated with the specific showing based on discount criteria.
 * @author David Burdjanadze
 * @version 1.0
*/
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
        return this.getMovieFee() - this.getDiscount();
    }

    /** Calculates the discount associated with a specific showing. Discount can be applied 
     * if the movie is a special one (per code), time of showing's start time, and if the movie
     * is of a sequence that has a discount associated with it. If multiple discounts may be applied
     * the largest one is applied. 
     * 
     * @return the discount to be applied
     */
    private double getDiscount() {
        double specialDiscount = 0; 
        double midDayDiscount = 0;
        if (this.movie.isSpecialMovie()) {
            specialDiscount = this.getMovieFee() * 0.2;  // 20% discount for special movie
        }
        // checking if time is between 11 am to 4 pm
        if(this.showStartTime.getHour() >= 11 && this.showStartTime.getHour() <= 16) {
            midDayDiscount = this.getMovieFee() * 0.25; // 25% discount for movies starting between 11 am to 4 pm
        }

        double sequenceDiscount = 0;
        if (this.sequenceOfTheDay == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (this.sequenceOfTheDay == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (this.sequenceOfTheDay == 7) {
            sequenceDiscount = 1; // $1 discount for 7th show
        }

        // biggest discount wins
        double percentageDiscount = Math.max(specialDiscount, midDayDiscount);
        return percentageDiscount > sequenceDiscount ? percentageDiscount : sequenceDiscount;
    }

}