package practicumopdracht.data;

import practicumopdracht.models.RestaurantPhoneBook;

import java.io.*;
import java.time.LocalDate;

/**
 * Text DAO for RestaurantPhoneBook objects.
 */
public class TextRestaurantPhoneBookDAO extends RestaurantPhoneBookDAO {
    private static final String FILE_NAME = "src/practicumopdracht/data/files/restaurantPhoneBook.txt";

    /**
     * Saves the RestaurantPhoneBook objects to a text file.
     *
     * @return boolean true if successful, false if not
     */
    @Override
    public boolean save() {
        try (FileWriter fileWriter = new FileWriter(FILE_NAME);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (RestaurantPhoneBook restaurantPhoneBook : this.objects) {
                for (String field : restaurantPhoneBook.getFields()) {
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
     * Loads the RestaurantPhoneBook objects from a text file.
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
                RestaurantPhoneBook restaurantPhoneBook = new RestaurantPhoneBook(
                        fields[0],
                        fields[1],
                        Integer.parseInt(fields[2]),
                        Double.parseDouble(fields[3]),
                        LocalDate.parse(fields[4]),
                        Boolean.parseBoolean(fields[5])
                );
                this.objects.add(restaurantPhoneBook);
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
