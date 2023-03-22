package practicumopdracht.data;

public class TextRestaurantContactDAO extends RestaurantContactDAO{
    private static final String FILE_NAME = "practicumopdracht/data/restaurantContact.txt";
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }
}
