package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.AddressNameComparator;
import practicumopdracht.comparators.ContactNameComparator;
import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;
import practicumopdracht.views.RestaurantContactView;
import practicumopdracht.views.View;

/**
 * Controller for RestaurantContactView.
 */
public class RetaurantContactController extends Controller {
    private final RestaurantContactView view;

    public RetaurantContactController(RestaurantPhoneBook restaurantPhoneBook) {
        this.view = new RestaurantContactView();

        view.getSaveButton().setOnAction(event -> handleSave());
        view.getNewButton().setOnAction(event -> handleNewRestaurantContact());
        view.getDeleteButton().setOnAction(event -> handleDelete());
        view.getDeleteButton().setVisible(false);
        view.getRestaurantsViewButton().setOnAction(event -> handleSwitchView());
        view.getNameAscending().setOnAction(event -> handleSort(false, false));
        view.getNameDescending().setOnAction(event -> handleSort(false, true));
        view.getAddressNameAscending().setOnAction(event -> handleSort(true, false));
        view.getAddressNameDescending().setOnAction(event -> handleSort(true, true));

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

        FXCollections.sort(view.getRestaurantContactListView().getItems(),
                new ContactNameComparator(false));
    }

    @Override
    public View getView() {
        return view;
    }

    /**
     * Handles sorting of the listview.
     *
     * @param checkAddress if true, sort on address and name.
     * @param descending   if true, sort descending.
     */
    private void handleSort(boolean checkAddress, boolean descending) {
        if (checkAddress) {
            FXCollections.sort(view.getRestaurantContactListView().getItems(), new AddressNameComparator(descending));
        } else {
            FXCollections.sort(view.getRestaurantContactListView().getItems(), new ContactNameComparator(descending));
        }
    }

    /**
     * Handles saving of a restaurant contact.
     */
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

    /**
     * Clears the text-fields and listview selection.
     */
    private void handleNewRestaurantContact() {
        this.view.getRestaurantContactListView().getSelectionModel().clearSelection();
        view.getNameField().setText("");
        view.getPhoneNumberField().setText("");
        view.getAddressField().setText("");
    }

    /**
     * Handles the deletion of a restaurant contact.
     */
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

    /**
     * Switches the view to the restaurant phone book view.
     */
    private void handleSwitchView() {
        MainApplication.switchController(new RestaurantPhoneBookController());
    }

    /**
     * Validates the text-fields.
     *
     * @return a string with the mistakes if any are found.
     */
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
            view.getNameField().setStyle("-fx-border-color: grey");
            view.getPhoneNumberField().setStyle("-fx-border-color: grey");
            view.getAddressField().setStyle("-fx-border-color: grey");
        }

        return mistakes.toString();
    }
}
