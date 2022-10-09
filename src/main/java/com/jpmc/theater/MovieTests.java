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
    
    /** Tests that for a special movie isSpecialMovie retruns true. */
    @Test
    void testSpecialMovie() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        boolean isSpecial = movie.isSpecialMovie();
        assertTrue(isSpecial);
    }
    
    /** Tests that for a non-special movie isSpecialMovie retruns false. */
    @Test
    void testNotSpecialMovie() {
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 0);
        boolean isSpecial = movie.isSpecialMovie();
        assertFalse(isSpecial);
    }

    /** Testing instantiating a movie erroneously results in an exception thrown. */
    @Test
    void testConstructMovieErroneously() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Movie("Spider-Man: No Way Home", null, 10, 0));
        assertEquals("Must have a title, running time, and a valid ticket price", exception.getMessage());
    }

    /** Tests for movie equality given all fields are identical. */
    @Test
    void testMoviesIdentical() {
        Movie spiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Movie identicalSpiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10, 1); 
        boolean isEqual = spiderManMovie.equals(identicalSpiderMovie);
        assertTrue(isEqual);
    }

    /** Tests for movie equality given difference in a field. */
    @Test
    void testMoviesNotIdentical() {
        Movie spiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, 1);
        Movie nearlyIdenticalSpiderManMovie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),11, 1); 
        boolean isEqual = spiderManMovie.equals(nearlyIdenticalSpiderMovie);
        assertFalse(isEqual);
    }
}