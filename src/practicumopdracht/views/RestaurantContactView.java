package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import practicumopdracht.models.RestaurantContact;
import practicumopdracht.models.RestaurantPhoneBook;

import static java.lang.Integer.MAX_VALUE;

public class RestaurantContactView extends View {

    @Override
    protected void initializeView() {
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

        ComboBox<RestaurantPhoneBook> restaurantsComboBox = new ComboBox<>();
        restaurantsComboBox.setPrefWidth(MAX_VALUE);
        restaurantsComboBox.setPromptText("Select restaurant");
        Label restaurantsLabel = new Label("Restaurants:");
        restaurantsLabel.setFont(largeFont);
        restaurantsLabel.setLabelFor(restaurantsComboBox);
        HBox restaurantsBox = new HBox();
        restaurantsBox.setAlignment(Pos.CENTER);
        restaurantsBox.getChildren().addAll(restaurantsLabel, restaurantsComboBox);

        TextField nameField = new TextField();
        nameField.setPromptText("Restaurant name");
        Label nameLabel = new Label("Name:");
        nameLabel.setLabelFor(nameField);
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(nameLabel, nameField);

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Insert number");
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setLabelFor(phoneNumberField);
        HBox phoneNumberBox = new HBox();
        phoneNumberBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

        HBox nameAndPhoneBox = new HBox();
        nameAndPhoneBox.getChildren().addAll(nameBox, phoneNumberBox);

        TextArea addressField = new TextArea();
        addressField.setPromptText("Restaurant address");
        addressField.setPrefRowCount(3);
        addressField.setPrefColumnCount(20);
        Label addressLabel = new Label("Address:");
        addressLabel.setLabelFor(addressField);
        VBox addressBox = new VBox();
        addressBox.getChildren().addAll(addressLabel, addressField);

        Button saveButton = new Button("Save");

        ListView<RestaurantContact> restaurantContactListView = new ListView<>();
        restaurantContactListView.setPrefHeight(100);
        restaurantContactListView.setPrefWidth(MAX_VALUE);
        restaurantContactListView.setPlaceholder(new Label("No restaurant contacts added yet."));

        Button newButton = new Button("New");
        Button deleteButton = new Button("Delete");
        Button restaurantsViewButton = new Button("Go to restaurants");
        HBox listButtonBox = new HBox();
        listButtonBox.getChildren().addAll(deleteButton, newButton, restaurantsViewButton);

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
            if (node instanceof HBox) {
                ((HBox) node).setAlignment(Pos.CENTER);
                ((HBox) node).setSpacing(10);

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

        root = mainPanel;
    }
}
