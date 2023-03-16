package practicumopdracht.data;

import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;

import java.util.List;

public class RestaurantContactDAO extends DAO<RestaurantContact> {
    protected RestaurantContactDAO() {
    }

    public List<RestaurantContact> getAllFor(RestaurantPhoneBook restaurantPhoneBook) {
        return this.objects.stream()
                .filter(restaurantContact -> restaurantContact.getBelongsTo().equals(restaurantPhoneBook))
                .toList();
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
