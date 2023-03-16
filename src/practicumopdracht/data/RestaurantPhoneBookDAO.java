package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;


public class RestaurantPhoneBookDAO extends DAO<RestaurantPhoneBook> {

    public RestaurantPhoneBook getById(int id) {
        return id >= 0 && id < this.objects.size() ? this.objects.get(id) : null;
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
