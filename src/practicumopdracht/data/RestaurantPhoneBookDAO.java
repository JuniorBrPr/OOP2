package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;


public abstract class RestaurantPhoneBookDAO extends DAO<RestaurantPhoneBook> {

    public RestaurantPhoneBook getById(int id) {
        return id >= 0 && id < this.objects.size() ? this.objects.get(id) : null;
    }

    public int getIdFor(RestaurantPhoneBook restaurantPhoneBook) {
        return this.objects.indexOf(restaurantPhoneBook);
    }
}
