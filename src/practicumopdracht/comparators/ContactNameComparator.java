package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantContact;

import java.util.Comparator;

public class ContactNameComparator implements Comparator<RestaurantContact> {
    private final boolean sortDescending;

    public ContactNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    @Override
    public int compare(RestaurantContact o1, RestaurantContact o2) {
        return this.sortDescending ? o2.getName().compareTo(o1.getName()) : o1.getName().compareTo(o2.getName());
    }
}
