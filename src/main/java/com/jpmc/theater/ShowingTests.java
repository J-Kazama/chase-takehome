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