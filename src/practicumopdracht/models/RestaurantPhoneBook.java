package practicumopdracht.models;

import java.time.LocalDate;

public class RestaurantPhoneBook {
    private String name;
    private String cuisine;
    private int tables;
    private double rating;
    private LocalDate established;
    private boolean wheelchairAccessible;

    public RestaurantPhoneBook() {
    }

    public RestaurantPhoneBook(String name, String cuisine, int tables, double rating, LocalDate established, boolean wheelchairAccessible) {
        this.name = name;
        this.cuisine = cuisine;
        this.tables = tables;
        this.rating = rating;
        this.established = established;
        this.wheelchairAccessible = wheelchairAccessible;
    }

    @Override
    public String toString(){
        return String.format("""
                        RestaurantPhoneBook
                        \tName:                 %s
                        \tCuisine:              %s
                        \tTables:               %d
                        \tRating:               %.1f
                        \tEstablished:          %s
                        \tWheelchairAccessible: %s""",
                name, cuisine, tables, rating, established, wheelchairAccessible ? "Yes" : "No");
    }
}
