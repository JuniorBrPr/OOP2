package practicumopdracht.models;

import java.time.LocalDate;

/**
 * Model for RestaurantPhoneBook objects.
 */
public class RestaurantPhoneBook {
    private String name;
    private String cuisine;
    private int tables;
    private double rating;
    private LocalDate established;
    private boolean wheelchairAccessible;

    public RestaurantPhoneBook() {
    }

    /**
     * Constructor for RestaurantPhoneBook.
     *
     * @param name                 String
     * @param cuisine              String
     * @param tables               int
     * @param rating               double
     * @param established          LocalDate
     * @param wheelchairAccessible boolean
     */
    public RestaurantPhoneBook(String name, String cuisine, int tables, double rating, LocalDate established,
                               boolean wheelchairAccessible) {
        this.name = name;
        this.cuisine = cuisine;
        this.tables = tables;
        this.rating = rating;
        this.established = established;
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getTables() {
        return tables;
    }

    public double getRating() {
        return rating;
    }

    public LocalDate getEstablished() {
        return established;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setEstablished(LocalDate established) {
        this.established = established;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    /**
     * Returns the fields of the RestaurantPhoneBook object as an array of Strings.
     *
     * @return String[] fields
     */
    public String[] getFields() {
        return new String[]{name, cuisine, String.valueOf(tables), String.valueOf(rating),
                established.toString(), String.valueOf(wheelchairAccessible)};
    }

    @Override
    public String toString() {
        return String.format("(Restaurant)%nName: %-30s\tCuisine: %-30s\tRating: %-3.1f\tTables: %-4d" +
                        "\tWheelchair accessible: %-5s",
                name, cuisine, rating, tables,
                wheelchairAccessible ? "Yes" : "No");
    }
}
