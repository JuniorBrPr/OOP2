package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantContact;

import java.util.Comparator;

/**
 * Comparator for RestaurantContact objects.
 * Sorts on name.
 */
public class ContactNameComparator implements Comparator<RestaurantContact> {
    private final boolean sortDescending;

    public ContactNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    /**
     * Compares two RestaurantContact objects.
     * Sorts on name.
     *
     * @param o1 RestaurantContact object 1
     * @param o2 RestaurantContact object 2
     * @return int
     */
    @Override
    public int compare(RestaurantContact o1, RestaurantContact o2) {
        return this.sortDescending ? o2.getName().compareTo(o1.getName()) : o1.getName().compareTo(o2.getName());
    }
}
