package com.jpmc.theater;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateProviderTests {
    /** Tests that current date of local date provider is equal to expected current date. */
    @Test
    void testCurrentDate() {
        assertEquals(LocalDate.now(), LocalDateProvider.singleton().currentDate());
    }
}