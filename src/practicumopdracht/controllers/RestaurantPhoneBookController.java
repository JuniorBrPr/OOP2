package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantPhoneBook;
import practicumopdracht.views.RestaurantPhoneBookView;
import practicumopdracht.views.View;

import java.time.LocalDate;

public class RestaurantPhoneBookController extends Controller {
    private final RestaurantPhoneBookView view;

    public RestaurantPhoneBookController() {
        this.view = new RestaurantPhoneBookView();
        save();
        newRestaurant();
        delete();
        select();
    }

    @Override
    public View getView() {
        return view;
    }

    private void save() {
        view.getSaveButton().setOnAction(event -> {
            String mistakes = validate();
            if (!mistakes.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Mistakes found".toUpperCase());
                alert.setContentText(mistakes);
                alert.showAndWait();
            } else {
                RestaurantPhoneBook restaurantPhoneBook = new RestaurantPhoneBook(
                        view.getNameField().getText(),
                        view.getCuisineField().getText(),
                        Integer.parseInt(view.getTablesField().getText()),
                        Double.parseDouble(view.getRatingField().getText()),
                        view.getEstablishedField().getValue(),
                        view.getWheelchairAccessibleField().isSelected()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Saved");
                alert.setHeaderText("Saved".toUpperCase());
                alert.setContentText(restaurantPhoneBook.toString());
                alert.showAndWait();
                view.getNameField().setText("");
                view.getCuisineField().setText("");
                view.getTablesField().setText("");
                view.getRatingField().setText("");
                view.getEstablishedField().setValue(null);
                view.getWheelchairAccessibleField().setSelected(false);
            }
        });
    }

    private void newRestaurant() {
        view.getNewButton().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Restaurant");
            alert.setHeaderText("New Restaurant".toUpperCase());
            alert.showAndWait();
        });
    }

    private void delete() {
        view.getDeleteButton().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete".toUpperCase());
            alert.showAndWait();
        });
    }

    private void select() {
        view.getSelectButton().setOnAction(event -> MainApplication.switchController(new RetaurantContactController()));
    }

    private String validate() {
        boolean valid = true;
        StringBuilder mistakes = new StringBuilder("Please correct the following mistakes:\n");

        String cuisineFieldTxt = view.getCuisineField().getText();
        String ratingFieldTxt = view.getRatingField().getText();
        String tablesFieldTxt = view.getTablesField().getText();
        String nameFieldTxt = view.getNameField().getText();
        LocalDate dateFieldDate = view.getEstablishedField().getValue();

        if (cuisineFieldTxt.isBlank()) {
            view.getCuisineField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Cuisine is required!\n");
        } else {
            view.getCuisineField().setStyle("-fx-border-color: green");
        }

        if (ratingFieldTxt.isBlank()) {
            view.getRatingField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Rating is required!\n");
        } else if (!ratingFieldTxt.matches("^[0-9]*\\.?[0-9]*$")) {
            view.getRatingField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Rating must be a number!\n");
        } else if (ratingFieldTxt.matches("^[0-9]*\\.?[0-9]*$") && Double.parseDouble(ratingFieldTxt) > 10) {
            view.getRatingField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Rating can't be higher than 10.0!\n");
        } else if (ratingFieldTxt.matches("^-[0-9]*\\.?[0-9]*$") && Double.parseDouble(ratingFieldTxt) < 0) {
            view.getRatingField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Rating can't be lower than 0.0!\n");
        } else {
            view.getRatingField().setStyle("-fx-border-color: green");
        }

        if (tablesFieldTxt.isBlank()) {
            view.getTablesField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Tables is required!\n");
        } else if (!tablesFieldTxt.matches("^[0-9]*$")) {
            view.getTablesField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Tables must be a non-decimal number!\n");
        } else if (tablesFieldTxt.matches("^[0-9]*$") && Integer.parseInt(tablesFieldTxt) < 0) {
            view.getTablesField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Tables can't be lower than 0!\n");
        } else {
            view.getTablesField().setStyle("-fx-border-color: green");
        }

        if (nameFieldTxt.isBlank()) {
            view.getNameField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Name is required!\n");
        } else {
            view.getNameField().setStyle("-fx-border-color: green");
        }

        if (dateFieldDate == null) {
            view.getEstablishedField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Date is required and must be a valid date!\n");
        } else if (dateFieldDate.isAfter(LocalDate.now())) {
            view.getEstablishedField().setStyle("-fx-border-color: red");
            valid = false;
            mistakes.append("Date can't be in the future!\n");
        } else {
            view.getEstablishedField().setStyle("-fx-border-color: green");
        }

        if (valid) {
            mistakes.delete(0, mistakes.length());
        }

        return mistakes.toString();
    }
}
