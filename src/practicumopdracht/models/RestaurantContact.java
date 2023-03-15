package practicumopdracht.models;


public class RestaurantContact {
    private RestaurantPhoneBook belongsTo;
    private String name;
    private String phoneNumber;
    private String address;

    public RestaurantContact(RestaurantPhoneBook belongsTo, String name, String phoneNumber, String address) {
        this.belongsTo = belongsTo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("""
                        RestaurantContact
                        \tName:        %s
                        \tPhoneNumber: %s
                        \tAddress:     %s
                        
                        \tBelongsTo:   %s""",
                name, phoneNumber, address, belongsTo);
    }
}
