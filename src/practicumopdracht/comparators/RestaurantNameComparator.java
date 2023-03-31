package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantPhoneBook;

import java.util.Comparator;

public class RestaurantNameComparator implements Comparator<RestaurantPhoneBook> {
    private final boolean sortDescending;

    public RestaurantNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(RestaurantPhoneBook o1, RestaurantPhoneBook o2) {
        return this.sortDescending ? o2.getName().compareTo(o1.getName()) : o1.getName().compareTo(o2.getName());
    }
}
