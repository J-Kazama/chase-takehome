package com.jpmc.theater;

import java.time.LocalDate;

/** A local date provider class for JPMorgan Chase's take home assignment. The local date provider 
 * class implements the singleton pattern and wraps the local date class.
 * @author David Burdjanadze
 * @version 1.0
*/
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