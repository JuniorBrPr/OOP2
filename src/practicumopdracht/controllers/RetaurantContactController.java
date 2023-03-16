package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;
import practicumopdracht.views.RestaurantContactView;
import practicumopdracht.views.View;

public class RetaurantContactController extends Controller {
    private final RestaurantContactView view;

    public RetaurantContactController(RestaurantPhoneBook restaurantPhoneBook) {
        this.view = new RestaurantContactView();

        view.getSaveButton().setOnAction(event -> handleSave());
        view.getNewButton().setOnAction(event -> handleNewRestaurantContact());
        view.getDeleteButton().setOnAction(event -> handleDelete());
        view.getRestaurantsViewButton().setOnAction(event -> handleSwitchView());
        view.getRestaurantsComboBox().setItems(FXCollections
                .observableArrayList(MainApplication.getRestaurantPhoneBookDAO().getAll()));
        view.getRestaurantsComboBox().getSelectionModel().select(restaurantPhoneBook);

        view.getRestaurantContactListView().setItems(FXCollections
                .observableArrayList(MainApplication.getRestaurantContactDAO().getAllFor(restaurantPhoneBook)));

        view.getRestaurantContactListView().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        view.getNameField().setText(newValue.getName());
                        view.getPhoneNumberField().setText(newValue.getPhoneNumber());
                        view.getAddressField().setText(newValue.getAddress());
                    }
                });
    }

    @Override
    public View getView() {
        return view;
    }

    private void handleSave() {
        String mistakes = validate();
        if (!mistakes.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Mistakes found".toUpperCase());
            alert.setContentText(mistakes);
            alert.showAndWait();
        } else if (view.getRestaurantContactListView().getSelectionModel().getSelectedItem() != null) {
//            view.getRestaurantContactListView().getSelectionModel().getSelectedItem()
//                    .setName(view.getNameField().getText());
//            view.getRestaurantContactListView().getSelectionModel().getSelectedItem()
//                    .setPhoneNumber(view.getPhoneNumberField().getText());
//            view.getRestaurantContactListView().getSelectionModel().getSelectedItem()
//                    .setAddress(view.getAddressField().getText());
//            view.getRestaurantContactListView().refresh();
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Saved");
//            alert.setHeaderText("Saved".toUpperCase());
//            alert.setContentText(view.getRestaurantContactListView().getSelectionModel().getSelectedItem().toString());
//            alert.showAndWait();
//            view.getNameField().setText("");
//            view.getPhoneNumberField().setText("");
//            view.getAddressField().setText("");
        } else if (view.getRestaurantContactListView().getSelectionModel().getSelectedItem() == null) {
            RestaurantContact restaurantContact = new RestaurantContact(
                    view.getRestaurantsComboBox().getSelectionModel().getSelectedItem(),
                    view.getNameField().getText(),
                    view.getPhoneNumberField().getText(),
                    view.getAddressField().getText()
            );
            MainApplication.getRestaurantContactDAO().addOrUpdate(restaurantContact);
            view.getRestaurantContactListView().getItems().add(restaurantContact);
            view.getRestaurantContactListView().refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Saved".toUpperCase());
            alert.setContentText(restaurantContact.toString());
            alert.showAndWait();
            view.getNameField().setText("");
            view.getPhoneNumberField().setText("");
            view.getAddressField().setText("");
        }
    }

    private void handleNewRestaurantContact() {
        this.view.getRestaurantContactListView().getSelectionModel().clearSelection();
        view.getNameField().setText("");
        view.getPhoneNumberField().setText("");
        view.getAddressField().setText("");
    }

    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete".toUpperCase());
        alert.showAndWait();
    }

    private void handleSwitchView() {
        MainApplication.switchController(new RestaurantPhoneBookController());
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
