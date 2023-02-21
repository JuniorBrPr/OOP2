package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import practicumopdracht.models.RestaurantContact;

import static java.lang.Integer.MAX_VALUE;

public class RestaurantContactView extends View {

    private TextField nameField;
    private ComboBox<String> cuisineField;
    private TextField phoneNumberField;
    private TextArea addressField;
    private TextField tablesField;
    private TextField ratingField;
    private DatePicker establishedField;
    private CheckBox wheelchairAccessibleField;
    private ListView<RestaurantContact> restaurantContactListView;
    private Button saveButton;
    private Button deleteButton;
    private Button newButton;
    private Button cancelButton;

    @Override
    protected void initializeView() {
        GridPane mainPanel = new GridPane();
        mainPanel.setPadding(new Insets(10));
        mainPanel.setAlignment(Pos.CENTER);
        mainPanel.setHgap(20);
        mainPanel.setVgap(25);

        Font smallFont = Font.font("Helvetica", FontWeight.BLACK, 16);
        Font largeFont = Font.font("Helvetica", FontWeight.BOLD, 36);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
                )));

        Label titleLabel = new Label("Restaurant Contact");
        titleLabel.setFont(largeFont);
        titleLabel.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(titleLabel);

        HBox nameBox = new HBox();
        nameBox.setSpacing(10);
        nameBox.setAlignment(Pos.CENTER_RIGHT);

        Label nameLabel = new Label("Name:");
        nameLabel.setFont(smallFont);
        nameLabel.setLabelFor(nameField);
        nameLabel.setAlignment(Pos.CENTER_RIGHT);

        nameField = new TextField();
        nameField.setPromptText("Restaurant name");

        nameBox.getChildren().addAll(nameLabel, nameField);

        HBox cuisineBox = new HBox();
        cuisineBox.setSpacing(10);
        cuisineBox.setAlignment(Pos.TOP_LEFT);

        Label cuisineLabel = new Label("Cuisine:");
        cuisineLabel.setFont(smallFont);
        cuisineLabel.setLabelFor(cuisineField);
        cuisineLabel.setAlignment(Pos.CENTER_RIGHT);

        cuisineField = new ComboBox<>();
        cuisineField.getItems()
                .addAll("Chinese", "Italian", "Japanese", "Korean", "Mexican", "Thai", "Vietnamese");
        cuisineField.setPromptText("Select cuisine");

        cuisineBox.getChildren().addAll(cuisineLabel, cuisineField);

        HBox phoneNumberBox = new HBox();
        phoneNumberBox.setSpacing(10);
        phoneNumberBox.setAlignment(Pos.CENTER_RIGHT);

        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setFont(smallFont);
        phoneNumberLabel.setLabelFor(phoneNumberField);
        phoneNumberLabel.setAlignment(Pos.CENTER_RIGHT);

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Insert number");

        phoneNumberBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

        HBox tablesBox = new HBox();
        tablesBox.setAlignment(Pos.CENTER_RIGHT);
        tablesBox.setSpacing(10);

        Label tablesLabel = new Label("Tables:");
        tablesLabel.setFont(smallFont);
        tablesLabel.setLabelFor(tablesField);
        tablesLabel.setAlignment(Pos.CENTER_RIGHT);

        tablesField = new TextField();
        tablesField.setPromptText("Number of tables");

        tablesBox.getChildren().addAll(tablesLabel, tablesField);

        HBox ratingBox = new HBox();
        ratingBox.setAlignment(Pos.BOTTOM_RIGHT);
        ratingBox.setSpacing(10);

        Label ratingLabel = new Label("Rating:");
        ratingLabel.setFont(smallFont);
        ratingLabel.setLabelFor(ratingField);
        ratingLabel.setAlignment(Pos.CENTER_RIGHT);

        ratingField = new TextField();
        ratingField.setPromptText("1.0 - 5.0");

        ratingBox.getChildren().addAll(ratingLabel, ratingField);

        VBox addressBox = new VBox();
        addressBox.setAlignment(Pos.CENTER_LEFT);
        addressBox.setSpacing(10);

        Label addressLabel = new Label("Address:");
        addressLabel.setFont(smallFont);
        addressLabel.setLabelFor(addressField);

        addressField = new TextArea();
        addressField.setPromptText("Restaurant address");
        addressField.setPrefRowCount(3);
        addressField.setPrefColumnCount(20);

        addressBox.getChildren().addAll(addressLabel, addressField);

        HBox establishedBox = new HBox();
        establishedBox.setSpacing(10);
        establishedBox.setAlignment(Pos.CENTER_RIGHT);

        Label establishedLabel = new Label("Established:");
        establishedLabel.setFont(smallFont);
        establishedLabel.setLabelFor(establishedField);
        establishedLabel.setAlignment(Pos.CENTER_LEFT);

        establishedField = new DatePicker();
        establishedField.setPromptText("Date of establishment");

        establishedBox.getChildren().addAll(establishedLabel, establishedField);

        HBox wheelchairAccessibleBox = new HBox();
        wheelchairAccessibleBox.setSpacing(10);
        wheelchairAccessibleBox.setAlignment(Pos.CENTER_LEFT);

        Label wheelchairAccessibleLabel = new Label("Wheelchair Accessible:");
        wheelchairAccessibleLabel.setFont(smallFont);
        wheelchairAccessibleLabel.setLabelFor(wheelchairAccessibleField);

        wheelchairAccessibleField = new CheckBox();

        wheelchairAccessibleBox.getChildren().addAll(wheelchairAccessibleLabel, wheelchairAccessibleField);

        VBox vContainerLeft = new VBox();
        vContainerLeft.setSpacing(10);
        vContainerLeft.setAlignment(Pos.CENTER_LEFT);
        vContainerLeft.setPrefWidth(MAX_VALUE);
        vContainerLeft.getChildren().addAll(nameBox, phoneNumberBox, tablesBox, ratingBox, establishedBox);

        VBox vContainerRight = new VBox();
        vContainerRight.setAlignment(Pos.CENTER_RIGHT);
        vContainerRight.setSpacing(10);
        vContainerRight.setPrefWidth(MAX_VALUE);
        vContainerRight.getChildren().addAll(cuisineBox, addressBox, wheelchairAccessibleBox);

        VBox listContainer = new VBox();

        restaurantContactListView = new ListView<>();
        restaurantContactListView.setPrefHeight(100);
        restaurantContactListView.setPlaceholder(new Label("No restaurant contacts added yet."));

        HBox listButtonBox = new HBox();
        listButtonBox.setSpacing(10);

        deleteButton = new Button("Delete");
        deleteButton.setPrefWidth(MAX_VALUE);
        deleteButton.setFont(smallFont);
        deleteButton.setAlignment(Pos.CENTER);

        newButton = new Button("New");
        newButton.setPrefWidth(MAX_VALUE);
        newButton.setFont(smallFont);
        newButton.setAlignment(Pos.CENTER);

        listButtonBox.getChildren().addAll(deleteButton, newButton);
        listContainer.getChildren().addAll(restaurantContactListView, listButtonBox);

        cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(MAX_VALUE);
        cancelButton.setFont(smallFont);
        cancelButton.setAlignment(Pos.CENTER);

        saveButton = new Button("Save");
        saveButton.setPrefWidth(MAX_VALUE);
        saveButton.setFont(smallFont);
        saveButton.setAlignment(Pos.CENTER);

        mainPanel.add(titleBox, 0, 0, 2, 1);
        mainPanel.add(vContainerLeft, 0, 1);
        mainPanel.add(vContainerRight, 1, 1);
        mainPanel.add(saveButton, 0, 2, 2, 1);
        mainPanel.add(listContainer, 0, 3, 2, 1);
        mainPanel.add(cancelButton, 0, 4, 2, 1);

        root = mainPanel;
    }
}
