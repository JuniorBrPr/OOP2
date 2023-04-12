package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantContact;

import java.util.Comparator;

/**
 * Comparator for RestaurantContact objects.
 * Sorts on address and name.
 */
public class AddressNameComparator implements Comparator<RestaurantContact> {
    private final boolean sortDescending;

    public AddressNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    /**
     * Compares two RestaurantContact objects.
     * Sorts on address and name.
     *
     * @param o1 RestaurantContact object 1
     * @param o2 RestaurantContact object 2
     * @return int
     */
    @Override
    public int compare(RestaurantContact o1, RestaurantContact o2) {
        if (this.sortDescending) {
            int result = o2.getAddress().compareTo(o1.getAddress());
            return result == 0 ? o2.getName().compareTo(o1.getName()) : result;
        } else {
            int result = o1.getAddress().compareTo(o2.getAddress());
            return result == 0 ? o1.getName().compareTo(o2.getName()) : result;
        }
    }
}
