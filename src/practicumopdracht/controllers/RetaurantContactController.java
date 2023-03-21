package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        view.getDeleteButton().setVisible(false);
        view.getRestaurantsViewButton().setOnAction(event -> handleSwitchView());

        view.getRestaurantsComboBox().setItems(FXCollections
                .observableArrayList(MainApplication.getRestaurantPhoneBookDAO().getAll()));
        view.getRestaurantsComboBox().getSelectionModel().select(restaurantPhoneBook);
        view.getRestaurantsComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        view.getRestaurantContactListView().setItems(FXCollections
                                .observableArrayList(MainApplication.getRestaurantContactDAO().getAllFor(newValue)));
                    }
                });

        view.getRestaurantContactListView().setItems(FXCollections
                .observableArrayList(MainApplication.getRestaurantContactDAO().getAllFor(restaurantPhoneBook)));

        view.getRestaurantContactListView().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    this.view.getDeleteButton().setVisible(observable != null);
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
            view.getAddressField().setText("");
        } else {
            RestaurantContact restaurantContact =
                    view.getRestaurantContactListView().getSelectionModel().getSelectedItem() == null ?
                            new RestaurantContact() :
                            view.getRestaurantContactListView().getSelectionModel().getSelectedItem();
            restaurantContact.setName(view.getNameField().getText());
            restaurantContact.setPhoneNumber(view.getPhoneNumberField().getText());
            restaurantContact.setAddress(view.getAddressField().getText());
            restaurantContact.setBelongsTo(view.getRestaurantsComboBox().getSelectionModel().getSelectedItem());

            MainApplication.getRestaurantContactDAO().addOrUpdate(restaurantContact);
            view.getRestaurantContactListView().setItems(FXCollections
                    .observableArrayList(MainApplication.getRestaurantContactDAO()
                            .getAllFor(restaurantContact.getBelongsTo())));

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
        RestaurantContact restaurantContact = view.getRestaurantContactListView().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + restaurantContact + " ?",
                ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            MainApplication.getRestaurantContactDAO().remove(restaurantContact);
            view.getRestaurantContactListView().setItems(FXCollections
                    .observableArrayList(MainApplication.getRestaurantContactDAO()
                            .getAllFor(restaurantContact.getBelongsTo())));
            view.getRestaurantContactListView().getSelectionModel().clearSelection();
            view.getNameField().setText("");
            view.getPhoneNumberField().setText("");
            view.getAddressField().setText("");
            view.getDeleteButton().setVisible(false);
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }
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
            view.getNameField().setStyle("-fx-border-color: default");
            view.getPhoneNumberField().setStyle("-fx-border-color: default");
            view.getAddressField().setStyle("-fx-border-color: default");
        }

        return mistakes.toString();
    }

}
