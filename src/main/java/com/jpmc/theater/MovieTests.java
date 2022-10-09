package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieTests {
    
    /** Testing instantiating a movie erroneously results in an exception thrown. */
    @Test
    void testConstructMovieErroneously() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Movie("Spider-Man: No Way Home", null, 10, 0));
        assertEquals("Must have a title, running time, and a valid ticket price", exception.getMessage());
    }
    
    /** Tests for no discount as the movie does not have a sequence that has
     * a discount associated with it, nor the special movie code set or a mid
     * day view time
     */
    @Test
    void testRegularMovieNoDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }
    
    /** Tests for discount applied for mid day view time. */
    @Test
    void testRegularMovieMidDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }
    
    /** Tests for sequence disccount applied. */
    @Test
    void testRegularMovieSequenceDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 50)));
        assertEquals(8, spiderMan.calculateTicketPrice(showing));
    }

    /** Tests for special movie discount. */
    @Test
    void testSpecialMovieDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 50)));
        assertEquals(8, spiderMan.calculateTicketPrice(showing));
    }

    /** Tests for largest discount applied. For a special movie, that is viewing middle of the day, and that 
     * has sequence of 1, the discount should be largest value out of {moviePrice * 0.2, moviePrice * 0.25, 3}
     */
    @Test
    void testLargestDiscountApplied() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 50)));
        assertEquals(7, spiderMan.calculateTicketPrice(showing));
    }

    /** Tests for movie equality given all fields are identical. */
    @Test
    void testMoviesIdentical() {
        Movie spiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Movie identicalSpiderMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1); 
        boolean isEqual = spiderManMovie.equals(identicalSpiderMovie);
        assertTrue(isEqual);
    }

    /** Tests for movie equality given difference in a field. */
    @Test
    void testMoviesNotIdentical() {
        Movie spiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Movie nearlyIdenticalSpiderMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),11, 1); 
        boolean isEqual = spiderManMovie.equals(nearlyIdenticalSpiderMovie);
        assertFalse(isEqual);
    }
}