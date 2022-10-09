package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests {
    
    /** Testing reservation for a null customer raises an exception. */
    @Test
    void testReserveMovieNullCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> theater.reserve(null, 9, 0));
        assertEquals("trying to make a reservation for a null customer", exception.getMessage());
    }
    
    /** Testing theater creation with a null provider. */
    @Test
    void testConstructTheaterNullProvider() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Theater(null));
        assertEquals("provider cannot be null", exception.getMessage());
    }

    /** Testing reservation for an invalid (non-positive) ticket amount raises an exception. */
    @Test
    void testReserveMovieInvalidTicketAmount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> theater.reserve(john, 9, 0));
        assertEquals("amount of tickets to buy cannot be a non-positive number", exception.getMessage());
    }

    /**
     * Testing fee of a reservation for a regular movie not during middle of the
     * day and for a sequence that has no discount, such that no discount should be 
     * applied.
    */
    @Test
    void testReserveMovieNoDiscount() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 9, 4);
        assertEquals(reservation.totalFee(), 36);
    }

    /**
     * Testing fee of a reservation for a regular movie during middle of the day,
     * such that a 25% discout should be applied.
    */
    @Test
    void testReserveMovingDuiringMidDay() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 4, 4);
        assertEquals(reservation.totalFee(), 33);
    }

    /**
     * Testing fee of a reservation for a regular movie not during middle of the
     * day and for a sequence that has a discount associated with it, such a the associated
     * discount, $1, is applied.
    */
    @Test
    void testReserveMovieWithDiscountSequence() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 7, 4);
        assertEquals(reservation.totalFee(), 36);
    }

    /**
     * Testing fee of a reservation for a special movie not during middle of the
     * day, such that a 20% discout should be applied.
    */
    @Test
    void testReserveSpecialMovieOutsideOfMidDay() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 8, 4);
        assertEquals(reservation.totalFee(), 40);
    }

    /**
     * Testing fee of a reservation for a special movie during middle of the day,
     * such that a 25% discout should be applied.
    */
    @Test
    void testReserveSpecialMovieDuringMidDay() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 37.5);
    }
}