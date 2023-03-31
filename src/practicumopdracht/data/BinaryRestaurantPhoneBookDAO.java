package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;

import java.io.*;
import java.util.ArrayList;

public class BinaryRestaurantPhoneBookDAO extends RestaurantPhoneBookDAO {
    private final String FILE_NAME = "src/practicumopdracht/data/files/restaurantPhoneBook.ser";

    @Override
    public boolean save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(this.objects);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + FILE_NAME);
            e.printStackTrace();
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
             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileInputStream.readAllBytes());
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            this.objects = (ArrayList<RestaurantPhoneBook>) objectInputStream.readObject();
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
