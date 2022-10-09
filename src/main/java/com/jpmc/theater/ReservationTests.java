package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReservationTests {

    /** Calculate the fee for a reservation. */
    @Test
    void testTotalFee() {
        Customer customer = new Customer("John Doe", "test-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                5,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        );
        Reservation reservation = new Reservation(customer, showing, 5);
        assertEquals(reservation.totalFee(), 50);
    }

    /** Testing reservation for an invalid (non-positive) ticket amount raises an exception. */
    @Test
    void testReserveMovieInvalidTicketAmount() {
        Customer customer = new Customer("John Doe", "test-id");
        Showing showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50))
        );
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Reservation(customer, showing, 0));
        assertEquals("invalid ticket count, cannot be a non-positive number", exception.getMessage());
    }
}