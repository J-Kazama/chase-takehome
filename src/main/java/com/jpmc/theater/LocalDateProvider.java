package com.jpmc.theater;

import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /** Returns the instance of the singleton local date provider class.
     * 
     * @return instance of the local date provider
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }

    /** Gets the current date.
     * 
     * @return current date
     */
    public LocalDate currentDate() {
        return LocalDate.now();
    }
}