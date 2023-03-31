package practicumopdracht.models;

import practicumopdracht.MainApplication;

public class RestaurantContact {
    private RestaurantPhoneBook belongsTo;
    private String name;
    private String phoneNumber;
    private String address;

    public RestaurantContact() {
    }

    public RestaurantContact(RestaurantPhoneBook belongsTo, String name, String phoneNumber, String address) {
        this.belongsTo = belongsTo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public RestaurantPhoneBook getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(RestaurantPhoneBook belongsTo) {
        this.belongsTo = belongsTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String[] getFields() {
        return new String[]{Integer.toString(MainApplication.getRestaurantPhoneBookDAO().getIdFor(belongsTo)),
                name, phoneNumber, address};
    }

    @Override
    public String toString() {
        return String.format("Name: %-40s   PhoneNumber: %-20s", name, phoneNumber);
    }
}
