package practicumopdracht.models;


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

    //Todo: Remove or implement when saving a RestaurantContact
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

    @Override
    public String toString() {
        return String.format(""" 
                        Name:    %-40s  PhoneNumber: %-20s,
                        Address:  %-40s BelongsTo:   %-40s""",
                name, phoneNumber, address, belongsTo.getName());
    }
}
