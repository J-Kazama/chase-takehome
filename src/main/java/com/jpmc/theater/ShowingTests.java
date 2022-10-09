package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowingTests {

    /** Tests for no discount as the showing does not have a sequence that has
     * a discount associated with it, nor the special movie code set or a mid
     * day view time
     */
    @Test
    void testShowingNoDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)));
        assertEquals(10, showing.calculateFee());
    }
    
    /** Tests for discount applied for mid day view time. */
    @Test
    void testShowingRegularMovieMidDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50)));
        assertEquals(7.5, showing.calculateFee());
    }
    
    /** Tests for sequence disccount applied. */
    @Test
    void testShowingRegularMovieSequenceDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50)));
        assertEquals(8, showing.calculateFee());
    }

    /** Tests for special movie discount. */
    @Test
    void testShowingSpecialMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 50)));
        assertEquals(8, showing.calculateFee());
    }

    /** Tests for largest discount applied. For a special movie, that is viewing middle of the day, and that 
     * has sequence of 1, the discount should be largest value out of {moviePrice * 0.2, moviePrice * 0.25, 3}
     */
    @Test
    void testShowingLargestDiscountApplied() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 50)));
        assertEquals(7, showing.calculateFee());
    }

    /** Test that sequence of the showing matches expected sequence. */
    @Test
    void testIsSequence() {
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        );
        boolean isSequence = showing.isSequence(1);
        assertTrue(isSequence);
    }

    /** Test that sequence of the showing does not match a different sequence. */
    @Test
    void testIsNotSequence() {
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        );
        boolean isSequence = showing.isSequence(2);
        assertFalse(isSequence);
    }

    /** Testing showing instantiation with an invalid value throws an exception. */
    @Test
    void testConstructShowingInvalidValue() {
        new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        );
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Showing(
            new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
            0,
            LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        ));
        assertEquals("A showing must have a movie, a start time, and a valid, positive sequence", exception.getMessage());
    }
}