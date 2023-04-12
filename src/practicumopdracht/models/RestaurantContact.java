package practicumopdracht.models;

import practicumopdracht.MainApplication;

import java.io.*;

/**
 * RestaurantContact model.
 */
public class RestaurantContact implements Serializable {
    private transient RestaurantPhoneBook belongsTo;
    private String name;
    private String phoneNumber;
    private String address;

    public RestaurantContact() {
    }

    /**
     * Constructor for RestaurantContact.
     *
     * @param belongsTo   RestaurantPhoneBook object
     * @param name        String
     * @param phoneNumber String
     * @param address     String
     */
    public RestaurantContact(RestaurantPhoneBook belongsTo, String name, String phoneNumber, String address) {
        this.belongsTo = belongsTo;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Writes the RestaurantContact object to a file.
     *
     * @param out ObjectOutputStream
     * @throws IOException if an error occurs
     */
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.write(MainApplication.getRestaurantPhoneBookDAO().getIdFor(belongsTo));
        out.defaultWriteObject();
    }

    /**
     * Reads the RestaurantContact object from a file.
     *
     * @param in ObjectInputStream
     * @throws IOException            if an error occurs
     * @throws ClassNotFoundException if an error occurs
     */
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        belongsTo = MainApplication.getRestaurantPhoneBookDAO().getById(in.read());
        in.defaultReadObject();
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
        return String.format("(Contact)%nName: %-40s\tPhoneNumber: %-20s\tAddress: %-20s", name, phoneNumber, address);
    }
}
