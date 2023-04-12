package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;

import java.time.LocalDate;

/**
 * Dummy DAO for RestaurantPhoneBook objects.
 */
public class DummyRestaurantPhoneBookDAO extends RestaurantPhoneBookDAO {
    public DummyRestaurantPhoneBookDAO() {
    }

    /**
     * Mock save method.
     *
     * @return boolean
     */
    @Override
    public boolean save() {
        return false;
    }

    /**
     * Loads the RestaurantPhoneBook objects from hard coded data.
     *
     * @return boolean
     */
    @Override
    public boolean load() {
        this.objects.add(new RestaurantPhoneBook
                ("Restaurant 1", "Italian", 55, 5.0, LocalDate.now(), true));
        this.objects.add(new RestaurantPhoneBook
                ("Restaurant 2", "Mexican", 15, 7.0, LocalDate.now(), true));
        this.objects.add(new RestaurantPhoneBook
                ("Restaurant 3", "Chinese", 25, 4.0, LocalDate.now(), true));
        this.objects.add(new RestaurantPhoneBook
                ("Restaurant 4", "Chinese", 25, 4.0, LocalDate.now(), true));
        return true;
    }
}
