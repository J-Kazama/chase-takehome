package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

/** A movie class for JPMorgan Chase's take home assignment. The movie class will store the name
 * duration, and ticket price of a movie prior to any discount application. Movies may be special or not
 * incurring in different discount amounts.
 * @author David Burdjanadze
 * @version 1.0
*/
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

    /** Checks whether the movie is a special movie.
     * 
     * @return true if the movie is a special movie, false otherwise
     */
    public boolean isSpecialMovie(){
        return this.specialCode == MOVIE_CODE_SPECIAL;
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
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}