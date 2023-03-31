package practicumopdracht.data;

import practicumopdracht.models.RestaurantContact;

import java.io.*;
import java.util.ArrayList;

public class ObjectRestaurantContactDAO extends RestaurantContactDAO{
    private final String FILE_NAME = "src/practicumopdracht/data/files/restaurantContact.ser";

    @Override
    public boolean save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(this.objects);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + FILE_NAME);
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load() {
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            this.objects = (ArrayList<RestaurantContact>) objectInputStream.readObject();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_NAME);
            return false;
        } catch (IOException e) {
            System.out.println("Error reading file: " + FILE_NAME);
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
