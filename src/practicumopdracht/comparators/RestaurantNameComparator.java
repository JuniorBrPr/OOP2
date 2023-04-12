package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantPhoneBook;

import java.util.Comparator;

/**
 * Comparator for RestaurantPhoneBook objects.
 * Sorts on name.
 */
public class RestaurantNameComparator implements Comparator<RestaurantPhoneBook> {
    private final boolean sortDescending;

    public RestaurantNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    /**
     * Compares two RestaurantPhoneBook objects.
     * Sorts on name.
     *
     * @param o1 RestaurantPhoneBook object 1
     * @param o2 RestaurantPhoneBook object 2
     * @return int
     */
    @Override
    public int compare(RestaurantPhoneBook o1, RestaurantPhoneBook o2) {
        return this.sortDescending ? o2.getName().compareTo(o1.getName()) : o1.getName().compareTo(o2.getName());
    }
}
