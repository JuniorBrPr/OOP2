package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantContact;

import java.io.*;

/**
 * DAO for RestaurantContact objects.
 */
public class TextRestaurantContactDAO extends RestaurantContactDAO {
    private static final String FILE_NAME = "src/practicumopdracht/data/files/restaurantContact.txt";

    /**
     * Saves the RestaurantContact objects to a text file.
     *
     * @return boolean true if successful, false if not
     */
    @Override
    public boolean save() {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (RestaurantContact restaurantContact : this.objects) {
                for (String field : restaurantContact.getFields()) {
                    bufferedWriter.write(field);
                    bufferedWriter.write(";");
                }
                bufferedWriter.newLine();
            }
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

    /**
     * Loads the RestaurantContact objects from a text file.
     *
     * @return boolean true if successful, false if not
     */
    @Override
    public boolean load() {
        try (FileReader fileReader = new FileReader(FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(";");
                RestaurantContact restaurantContact = new RestaurantContact(
                        MainApplication.getRestaurantPhoneBookDAO().getById(Integer.parseInt(fields[0])),
                        fields[1],
                        fields[2],
                        fields[3]
                );
                this.objects.add(restaurantContact);
            }
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
