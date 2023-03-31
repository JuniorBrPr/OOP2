package practicumopdracht.comparators;

import practicumopdracht.models.RestaurantContact;

import java.util.Comparator;

public class AddressNameComparator implements Comparator<RestaurantContact> {
    private final boolean sortDescending;

    public AddressNameComparator(boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

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
