package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;

/**
 * DAO for RestaurantPhoneBook objects.
 */
public abstract class RestaurantPhoneBookDAO extends DAO<RestaurantPhoneBook> {

    /**
     * Gets the RestaurantPhoneBook object with the given id.
     *
     * @param id int
     * @return RestaurantPhoneBook object
     */
    public RestaurantPhoneBook getById(int id) {
        return id >= 0 && id < this.objects.size() ? this.objects.get(id) : null;
    }

    /**
     * Gets the id for a given RestaurantPhoneBook object.
     *
     * @param restaurantPhoneBook RestaurantPhoneBook object
     * @return int id
     */
    public int getIdFor(RestaurantPhoneBook restaurantPhoneBook) {
        return this.objects.indexOf(restaurantPhoneBook);
    }
}
