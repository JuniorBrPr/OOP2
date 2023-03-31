package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;

import java.io.*;
import java.time.LocalDate;

public class BinaryRestaurantPhoneBookDAO extends RestaurantPhoneBookDAO {
    private final String FILE_NAME = "src/practicumopdracht/data/files/restaurantPhoneBook.ser";

    @Override
    public boolean save() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
             DataOutputStream out = new DataOutputStream(fileOutputStream)) {
            out.writeInt(this.objects.size());
            for (RestaurantPhoneBook restaurantPhoneBook : this.objects) {
                out.writeUTF(restaurantPhoneBook.getName());
                out.writeUTF(restaurantPhoneBook.getCuisine());
                out.writeInt(restaurantPhoneBook.getTables());
                out.writeDouble(restaurantPhoneBook.getRating());
                out.writeUTF(restaurantPhoneBook.getEstablished().toString());
                out.writeBoolean(restaurantPhoneBook.isWheelchairAccessible());
            }
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
             DataInputStream in = new DataInputStream(fileInputStream)) {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                String name = in.readUTF();
                String cuisine = in.readUTF();
                int tables = in.readInt();
                double rating = in.readDouble();
                LocalDate established = LocalDate.parse(in.readUTF());
                boolean wheelchairAccessible = in.readBoolean();
                this.objects.add(
                        new RestaurantPhoneBook(name, cuisine, tables, rating, established, wheelchairAccessible));
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error reading file: " + FILE_NAME);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
