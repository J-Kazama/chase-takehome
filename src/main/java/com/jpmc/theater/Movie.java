package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /** Constructor for the movie class. A movie is composed of a title, length (running time), ticket price
     * and a code for whether it is a special movie or not (related to a special discount)
     * 
     * @param  title            title of the movie
     * @param  runningTime      the length of the movie
     * @param  ticketPrice      the price for a single ticket for this movie
     * @param  specialCode      the code for this movie
     * @throws RuntimeException if title or running time is missing, or ticket price is negative
     */
    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        if(title.equals("") || runningTime == null || ticketPrice < 0)
            throw new RuntimeException("Must have a title, running time, and a valid ticket price");
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /** Title getter.
     * 
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /** Running time getter.
     * 
     * @return the length of the movie
     */
    public Duration getRunningTime() {
        return runningTime;
    }

    /** Ticket price getter.
     * 
     * @return the price for a single ticket of the movie
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /** Calculates the price for a showing of this movie. The price of the showing would be the
     * price of the ticket and substracting any discount associated with that specific showing.
     * 
     * @param showing the showing of this movie
     * @return        the price of this showing
     */
    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing.getSequenceOfTheDay(), showing.getStartTime());
    }

    /** Calculates the discount associated with a specific showing. Discount can be applied 
     * if the movie is a special one (per code), time of showing's start time, and if the movie
     * is of a sequence that has a discount associated with it. If multiple discounts may be applied
     * the largest one is applied. 
     * 
     * @param showSequence  the number of showing of the current date in the theater
     * @param showStartTime the time when the showing begins
     * @return              the amount of the discount
     */
    private double getDiscount(int showSequence, LocalDateTime showStartTime) {
        double specialDiscount = 0; 
        double midDayDiscount = 0;
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        // checking if time is between 11 am to 4 pm
        if(showStartTime.getHour() >= 11 && showStartTime.getHour() <= 16) {
            midDayDiscount = ticketPrice * 0.25; // 25% discount for movies starting between 11 am to 4 pm
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if (showSequence == 7) {
            sequenceDiscount = 1; // $1 discount for 7th show
        }

        // biggest discount wins
        double percentageDiscount = Math.max(specialDiscount, midDayDiscount);
        return percentageDiscount > sequenceDiscount ? percentageDiscount : sequenceDiscount;
    }

    /** Overriding equality method to check for the fields of the movie for movie equality
     * when calling movie1.equals(movie2).
     *  
     * @param o object passed
     * @return  true or false depending of if fields match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    /** Overriding how a movie object is hashed.
     * 
     * @return the hashcode for the movie
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}