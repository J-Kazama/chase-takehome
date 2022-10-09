package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTests {
    
    /** Testing construction of an invalid customer, with an empty name. */
    @Test
    void testConstructCostumerEmptyName() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new Customer("", "john123"));
        assertEquals("Custoemr must have a non-empty name and id", exception.getMessage());
    }

    /** Tests for customer equality given identical fields. */
    @Test
    void testCustomerIdentical() {
        Customer john = new Customer("john", "john123");
        Customer sameJohn = new Customer("john", "john123");
        boolean isEqual = john.equals(sameJohn);
        assertTrue(isEqual);
    }

    /** Tests for customer equality given difference in a field. */
    @Test
    void testCustomerNotIdentical() {
        Customer john = new Customer("john", "john123");
        Customer otherJohn = new Customer("john", "john124");
        boolean isEqual = john.equals(otherJohn);
        assertFalse(isEqual);
    }
}
