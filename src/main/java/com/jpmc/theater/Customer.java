package com.jpmc.theater;

import java.util.Objects;

/** A customer class for JPMorgan Chase's take home assignment. The customer class is used
 * to facilitate reservation creation of movie showings at a theater. A customer has a name and an id.
 * @author David Burdjanadze
 * @version 1.0
*/
public class Customer {
    private String name;
    private String id;

    /** Constructor for the customer class.
     * 
     * @param  name             customer name
     * @param  id               customer id
     * @throws RuntimeException when name or id are empty
     */
    public Customer(String name, String id) {
        if(id.equals("") || name.equals(""))
            throw new RuntimeException("Custoemr must have a non-empty name and id");
        this.id = id;
        this.name = name;
    }

    /** Overrides equality method to test per fields of customer.
     * 
     * @param  o the object to compare against
     * @return   true or false depending on whether fields are identical
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    /** Overrides the hashcode method to use the fields of the customer class.
     * 
     * @return the hash code for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    /** Overrides the toString method to use the fields of the customer class.
     * 
     * @return a readable representation of the customer object
     */
    @Override
    public String toString() {
        return "name: " + name + ", " + "id: " + this.id;
    }
}