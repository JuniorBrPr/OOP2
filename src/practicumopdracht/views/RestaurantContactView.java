package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;

import static java.lang.Integer.MAX_VALUE;

public class RestaurantContactView extends View {
    private Button newButton;
    private Button saveButton;
    private Button deleteButton;
    private Button restaurantsViewButton;
    private ComboBox<RestaurantPhoneBook> restaurantsComboBox;
    private TextField nameField;
    private TextField phoneNumberField;
    private TextArea addressField;
    private ListView<RestaurantContact> restaurantContactListView;

    @Override
    protected Parent initializeView() {
        Font smallFont = Font.font("Helvetica", FontWeight.BLACK, 16);
        Font largeFont = Font.font("Helvetica", FontWeight.BOLD, 36);

        Label titleLabel = new Label("Restaurant Contact");
        titleLabel.setAlignment(Pos.CENTER);
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setPrefHeight(100);
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
                )));
        titleBox.getChildren().add(titleLabel);

        restaurantsComboBox = new ComboBox<>();
        restaurantsComboBox.setPrefWidth(MAX_VALUE);
        restaurantsComboBox.setPromptText("Select restaurant");
        Label restaurantsLabel = new Label("Restaurants:");
        restaurantsLabel.setFont(largeFont);
        restaurantsLabel.setLabelFor(restaurantsComboBox);
        HBox restaurantsBox = new HBox();
        restaurantsBox.setAlignment(Pos.CENTER);
        restaurantsBox.getChildren().addAll(restaurantsLabel, restaurantsComboBox);

        nameField = new TextField();
        nameField.setPromptText("Restaurant name");
        Label nameLabel = new Label("Name:");
        nameLabel.setLabelFor(nameField);
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(nameLabel, nameField);

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Insert number");
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setLabelFor(phoneNumberField);
        HBox phoneNumberBox = new HBox();
        phoneNumberBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

        HBox nameAndPhoneBox = new HBox();
        nameAndPhoneBox.getChildren().addAll(nameBox, phoneNumberBox);

        addressField = new TextArea();
        addressField.setPromptText("Restaurant address");
        addressField.setPrefRowCount(3);
        addressField.setPrefColumnCount(20);
        Label addressLabel = new Label("Address:");
        addressLabel.setLabelFor(addressField);
        VBox addressBox = new VBox();
        addressBox.getChildren().addAll(addressLabel, addressField);

        saveButton = new Button("Save");

        restaurantContactListView = new ListView<>();
        restaurantContactListView.setPrefHeight(220);
        restaurantContactListView.setPrefWidth(MAX_VALUE);
        restaurantContactListView.setPlaceholder(new Label("No restaurant contacts added yet."));

        this.newButton = new Button("New");
        this.deleteButton = new Button("Delete");
        this.restaurantsViewButton = new Button("Go to restaurants");
        HBox listButtonBox = new HBox();
        listButtonBox.getChildren().addAll(this.deleteButton, this.newButton, this.restaurantsViewButton);

        VBox mainPanel = new VBox();
        mainPanel.setSpacing(10);
        mainPanel.setPadding(new Insets(10));
        mainPanel.setPrefHeight(MAX_VALUE);
        mainPanel.setPrefWidth(MAX_VALUE);
        mainPanel.getChildren().addAll(titleBox, restaurantsBox, nameAndPhoneBox, addressBox, saveButton,
                restaurantContactListView, listButtonBox);

        mainPanel.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(MAX_VALUE);
                ((Button) node).setFont(smallFont);
            }
            if (node instanceof HBox hBox) {
                //TODO hBox var gebruiken
                hBox.setAlignment(Pos.CENTER);
                hBox.setSpacing(10);

                ((HBox) node).getChildren().forEach(child -> {
                    if (child instanceof Button) {
                        ((Button) child).setPrefWidth(MAX_VALUE);
                        ((Button) child).setFont(smallFont);
                    }
                    if (child instanceof Label) {
                        ((Label) child).setFont(smallFont);
                        ((Label) child).setAlignment(Pos.CENTER_LEFT);
                        ((Label) child).setMinWidth(100);
                    }

                    if (child instanceof HBox){
                        ((HBox) child).setAlignment(Pos.CENTER);
                        ((HBox) child).setSpacing(10);
                        ((HBox) child).getChildren().forEach(child2 -> {
                            if (child2 instanceof Label) {
                                ((Label) child2).setFont(smallFont);
                                ((Label) child2).setAlignment(Pos.CENTER_LEFT);
                                ((Label) child2).setMinWidth(120);
                            }
                            if (child2 instanceof TextField) {
                                ((TextField) child2).setAlignment(Pos.CENTER);
                                ((TextField) child2).setMinWidth(130);
                                ((TextField) child2).setPrefWidth(MAX_VALUE);
                            }
                        });
                    }
                });
            }
        });

        titleLabel.setFont(largeFont);

        return mainPanel;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getRestaurantsViewButton() {
        return restaurantsViewButton;
    }

    public ComboBox<RestaurantPhoneBook> getRestaurantsComboBox() {
        return restaurantsComboBox;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public TextArea getAddressField() {
        return addressField;
    }

    public ListView<RestaurantContact> getRestaurantContactListView() {
        return restaurantContactListView;
    }
}
