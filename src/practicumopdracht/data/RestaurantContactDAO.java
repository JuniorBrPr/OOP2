package practicumopdracht.data;

import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;

import java.util.List;

/**
 * DAO for RestaurantContact objects.
 */
public abstract class RestaurantContactDAO extends DAO<RestaurantContact> {

    /**
     * Gets all RestaurantContact objects for a given RestaurantPhoneBook.
     *
     * @param restaurantPhoneBook RestaurantPhoneBook object
     * @return List of RestaurantContact objects
     */
    public List<RestaurantContact> getAllFor(RestaurantPhoneBook restaurantPhoneBook) {
        return this.objects.stream()
                .filter(restaurantContact -> restaurantContact.getBelongsTo().equals(restaurantPhoneBook))
                .toList();
    }
}
