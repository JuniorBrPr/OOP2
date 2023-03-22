package practicumopdracht.data;

import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;

import java.util.List;

public abstract class RestaurantContactDAO extends DAO<RestaurantContact> {
    protected RestaurantContactDAO() {
    }

    public List<RestaurantContact> getAllFor(RestaurantPhoneBook restaurantPhoneBook) {
        return this.objects.stream()
                .filter(restaurantContact -> restaurantContact.getBelongsTo().equals(restaurantPhoneBook))
                .toList();
    }
}
