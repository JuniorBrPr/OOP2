package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantContact;

public class DummyRestaurantContactDAO extends RestaurantContactDAO {

    public DummyRestaurantContactDAO() {
    }

    @Override
    public boolean load() {
        this.objects.add
                (new RestaurantContact
                        (MainApplication.getRestaurantPhoneBookDAO()
                                .getById(0), "Restaurant 1", "123456789", "Adres 1"));
        this.objects.add(new RestaurantContact
                (MainApplication.getRestaurantPhoneBookDAO()
                        .getById(1), "Restaurant 2", "123456789", "Adres 2"));
        this.objects.add(new RestaurantContact
                (MainApplication.getRestaurantPhoneBookDAO()
                        .getById(2), "Restaurant 3", "123456789", "Adres 3"));
        this.objects.add(new RestaurantContact
                (MainApplication.getRestaurantPhoneBookDAO()
                        .getById(3), "Restaurant 4", "123456789", "Adres 4"));
        return true;
    }
}
