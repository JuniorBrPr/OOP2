package practicumopdracht.controllers;

import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;
import practicumopdracht.views.RestaurantContactView;
import practicumopdracht.views.View;

public class RetaurantContactController extends Controller {
    private RestaurantContactView view;

    public RetaurantContactController() {
        this.view = new RestaurantContactView();
        save();
        newRestaurantContact();
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
                RestaurantContact restaurantContact = new RestaurantContact(
                        new RestaurantPhoneBook(),
                        view.getNameField().getText(),
                        view.getPhoneNumberField().getText(),
                        view.getAddressField().getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Saved");
                alert.setHeaderText("Saved".toUpperCase());
                alert.setContentText(restaurantContact.toString());
                alert.showAndWait();
                view.getNameField().setText("");
                view.getPhoneNumberField().setText("");
                view.getAddressField().setText("");
            }
        });
    }

    private void newRestaurantContact() {
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
        view.getRestaurantsViewButton().setOnAction(event ->
                MainApplication.switchController(new RestaurantPhoneBookController()));
    }

    private String validate() {
        boolean valid = true;
        StringBuilder mistakes = new StringBuilder("Please correct the following mistakes:\n");

        String nameFieldTxt = view.getNameField().getText();
        String phoneNumberFieldTxt = view.getPhoneNumberField().getText();
        String addressFieldTxt = view.getAddressField().getText();

        if (nameFieldTxt.isBlank()) {
            view.getNameField().setStyle("-fx-border-color: red");
            mistakes.append("- Name is required!\n");
            valid = false;
        } else {
            view.getNameField().setStyle("-fx-border-color: green");
        }

        if (phoneNumberFieldTxt.isBlank()) {
            view.getPhoneNumberField().setStyle("-fx-border-color: red");
            mistakes.append("- Phone number is required!\n");
            valid = false;
        } else if (!phoneNumberFieldTxt.matches("[0-9]+")) {
            view.getPhoneNumberField().setStyle("-fx-border-color: red");
            mistakes.append("- Phone number can only contain numbers!\n");
            valid = false;
        } else {
            view.getPhoneNumberField().setStyle("-fx-border-color: green");
        }

        if (addressFieldTxt.isBlank()) {
            view.getAddressField().setStyle("-fx-border-color: red");
            mistakes.append("- Address is required!\n");
            valid = false;
        } else {
            view.getAddressField().setStyle("-fx-border-color: green");
        }

        if (valid) {
            mistakes.delete(0, mistakes.length());
        }

        return mistakes.toString();
    }
}
