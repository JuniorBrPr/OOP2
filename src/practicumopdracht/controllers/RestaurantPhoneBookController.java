package practicumopdracht.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import practicumopdracht.MainApplication;
import practicumopdracht.models.RestaurantPhoneBook;
import practicumopdracht.views.RestaurantPhoneBookView;
import practicumopdracht.views.View;

import java.time.LocalDate;

public class RestaurantPhoneBookController extends Controller {
    private final RestaurantPhoneBookView view;

    public RestaurantPhoneBookController() {
        this.view = new RestaurantPhoneBookView();
        view.getSaveButton().setOnAction(event -> handleSave());
        view.getNewButton().setOnAction(event -> handleNewRestaurant());
        view.getDeleteButton().setOnAction(event -> handleDelete());
        view.getDeleteButton().setVisible(false);
        view.getSelectButton().setOnAction(event -> handleSelect());
        view.getSaveMenuItem().setOnAction(event -> handleFileSave());
        view.getLoadMenuItem().setOnAction(event -> handleFileLoad());
        view.getExitMenuItem().setOnAction(event -> handleExit());

        view.getRestaurantPhoneBookList().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    this.view.getDeleteButton().setVisible(observable != null);
                    if (newValue != null) {
                        view.getNameField().setText(newValue.getName());
                        view.getCuisineField().setText(newValue.getCuisine());
                        view.getTablesField().setText(String.valueOf(newValue.getTables()));
                        view.getRatingField().setText(String.valueOf(newValue.getRating()));
                        view.getEstablishedField().setValue(newValue.getEstablished());
                        view.getWheelchairAccessibleField().setSelected(newValue.isWheelchairAccessible());
                    }
                });

        view.getRestaurantPhoneBookList().setItems(FXCollections.observableArrayList(
                MainApplication.getRestaurantPhoneBookDAO().getAll()));
    }

    private void handleExit() {
        handleFileSave();
        Platform.exit();
    }

    private void handleFileLoad() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Load");
        alert.setHeaderText("Load from file?");
        alert.setContentText("If you press \"YES\" ,all unsaved changes will be lost");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    if (MainApplication.getRestaurantPhoneBookDAO().load() &&
                            MainApplication.getRestaurantContactDAO().load()) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Load");
                        alert1.setHeaderText("Load from file");
                        alert1.setContentText("Load from file was successful");
                        view.getRestaurantPhoneBookList()
                                .setItems(FXCollections.observableArrayList(MainApplication.getRestaurantPhoneBookDAO()
                                        .getAll()));
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("Load");
                        alert1.setHeaderText("Load from file");
                        alert1.setContentText("Load from file was not successful");
                    }
                });
    }

    private void handleFileSave() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save");
        alert.setHeaderText("Save to file?");
        alert.setContentText("If you press \"YES\" ,all unsaved changes will be lost");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    MainApplication.getRestaurantPhoneBookDAO().save();
                    MainApplication.getRestaurantContactDAO().save();
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Save");
                    alert1.setHeaderText("Save to file");
                    alert1.setContentText("Save to file was successful");
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
        } else {

            RestaurantPhoneBook restaurantPhoneBook =
                    this.view.getRestaurantPhoneBookList().getSelectionModel().getSelectedItem() == null ?
                            new RestaurantPhoneBook() :
                            this.view.getRestaurantPhoneBookList().getSelectionModel().getSelectedItem();

            restaurantPhoneBook.setName(view.getNameField().getText());
            restaurantPhoneBook.setCuisine(view.getCuisineField().getText());
            restaurantPhoneBook.setTables(Integer.parseInt(view.getTablesField().getText()));
            restaurantPhoneBook.setRating(Double.parseDouble(view.getRatingField().getText()));
            restaurantPhoneBook.setEstablished(view.getEstablishedField().getValue());
            restaurantPhoneBook.setWheelchairAccessible(view.getWheelchairAccessibleField().isSelected());

            MainApplication.getRestaurantPhoneBookDAO().addOrUpdate(restaurantPhoneBook);

            this.view.getRestaurantPhoneBookList()
                    .setItems(FXCollections.observableArrayList(MainApplication.getRestaurantPhoneBookDAO().getAll()));

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

    }

    private void handleNewRestaurant() {
        view.getNameField().setText("");
        view.getCuisineField().setText("");
        view.getTablesField().setText("");
        view.getRatingField().setText("");
        view.getEstablishedField().setValue(null);
        view.getWheelchairAccessibleField().setSelected(false);
        view.getRestaurantPhoneBookList().getSelectionModel().clearSelection();
    }

    private void handleDelete() {
        RestaurantPhoneBook restaurantPhoneBook =
                this.view.getRestaurantPhoneBookList().getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + restaurantPhoneBook + " ?",
                ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            MainApplication.getRestaurantPhoneBookDAO().remove(restaurantPhoneBook);
            view.getRestaurantPhoneBookList().setItems(FXCollections
                    .observableArrayList(MainApplication.getRestaurantPhoneBookDAO().getAll()));
            view.getRestaurantPhoneBookList().getSelectionModel().clearSelection();

            view.getDeleteButton().setVisible(false);
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    private void handleSelect() {
        if (this.view.getRestaurantPhoneBookList().getSelectionModel().getSelectedItem() != null) {
            MainApplication.switchController(new RetaurantContactController(
                    this.view.getRestaurantPhoneBookList().getSelectionModel().getSelectedItem()));
        }
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
            view.getNameField().setStyle("-fx-border-color: default");
            view.getCuisineField().setStyle("-fx-border-color: default");
            view.getTablesField().setStyle("-fx-border-color: default");
            view.getRatingField().setStyle("-fx-border-color: default");
            view.getEstablishedField().setStyle("-fx-border-color: default");
        }

        return mistakes.toString();
    }
}
