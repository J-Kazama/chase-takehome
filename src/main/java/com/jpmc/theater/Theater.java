package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.json.JSONObject;

/** A theater class for JPMorgan Chase's take home assignment. The theater class creates a theater
 * that has a schedule composed of list of showings. The class facilitates movie ticket reservations. 
   @author David Burdjanadze
 * @version 1.0
*/
public class Theater {
    LocalDateProvider provider;
    private List<Showing> schedule;

    /** Theater class constructor.
     * 
     * @param  provider         a singleton class instance to provide date
     * @throws RuntimeException if object is constructed with a null provider
    */
    public Theater(LocalDateProvider provider) {
        if(provider == null)
            throw new RuntimeException("provider cannot be null");
        this.provider = provider;
        schedule = setupSchedule();
    }

    /** Setting up some movies and showings to create a default schedule.
     * 
     * @return the list of showings of the schedule.
    */
    private List<Showing> setupSchedule() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatman = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        
        return List.of(
            new Showing(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatman, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Showing(theBatman, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Showing(theBatman, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    /**
     * Reserves a movie ticket(s) for a customer. The method will first check that the argument
     * values provided are valid.
     * 
     * @param customer          the customer making the reservation
     * @param sequence          the number with the specific showing of the movie given for a date
     * @param howManyTickets    amount of tickets to buy
     * @throws RuntimeException for non-positive ticket amount
     * @throws RuntimeException for a null customer
     * @return                  a reservation object for this reservation
    */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        if(howManyTickets <= 0)
            throw new RuntimeException("amount of tickets to buy cannot be a non-positive number");
        if(customer == null)
            throw new RuntimeException("trying to make a reservation for a null customer");   
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    /** Prints the schedule of the theater for today in a plain text. */
    public void printSchedule() {
        System.out.println(provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }

    /** Prints the schedule of the theater for today in a JSON format */
    public void printScheduleInJSON() { 
        JSONObject scheduleInJSON = new JSONObject();
        int showingNumber = 0;
        schedule.forEach(s ->
            scheduleInJSON.put(++showingNumber, s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
        System.out.println(scheduleInJSON.toString());
        System.out.println("===================================================");
    }

    /** Prints the schedule of the theater in a readable format. */
    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    /** Adds a suffix to the handle plural value.
     * 
     * @param value amount of some unit of time
     * @return      either an empty string or an "s" depending on plurality
    */
    private String handlePlural(int value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    /** Main function will instantiate a theater object and print its schedule.
     * 
     * @param args command line input arguments
    */
    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }
}