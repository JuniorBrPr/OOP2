package practicumopdracht.views;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class RestaurantContactView extends View {

    private Label titleLabel;
    private Label nameLabel;
    private TextField nameField;
    private Label cuisineLabel;
    private ComboBox<String> cuisineField;
    private Label phoneNumberLabel;
    private TextField phoneNumberField;
    private Label addressLabel;
    private TextArea addressField;
    private Label tablesLabel;
    private TextField tablesField;
    private Label ratingLabel;
    private TextField ratingField;
    private Label establishedLabel;
    private DatePicker establishedField;
    private Label wheelchairAccessibleLabel;
    private CheckBox wheelchairAccessibleField;
    private Button saveButton;
    private Button cancelButton;


    @Override
    protected void initializeView() {
        VBox mainPanel = new VBox();
        mainPanel.setSpacing(20);

        Font smallFont = Font.font("Helvetica", FontWeight.BLACK, 16);
        Font largeFont = Font.font("Helvetica", FontWeight.BOLD, 36);

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
                )));

        titleLabel = new Label("Restaurant Contact");
        titleLabel.setFont(largeFont);
        titleLabel.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(titleLabel);

        HBox nameBox = new HBox();
        nameBox.setSpacing(10);
        nameBox.setAlignment(Pos.CENTER_RIGHT);

        nameLabel = new Label("Name:");
        nameLabel.setFont(smallFont);
        nameLabel.setLabelFor(nameField);
        nameLabel.setAlignment(Pos.CENTER_RIGHT);

        nameField = new TextField();
        nameField.setPromptText("Restaurant name");

        nameBox.getChildren().addAll(nameLabel, nameField);

        HBox cuisineBox = new HBox();
        cuisineBox.setSpacing(10);
        cuisineBox.setAlignment(Pos.CENTER);

        cuisineLabel = new Label("Cuisine:");
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

        phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setFont(smallFont);
        phoneNumberLabel.setLabelFor(phoneNumberField);
        phoneNumberLabel.setAlignment(Pos.CENTER_RIGHT);

        phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Insert number");

        phoneNumberBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

        HBox tablesBox = new HBox();
        tablesBox.setAlignment(Pos.CENTER_RIGHT);
        tablesBox.setSpacing(10);

        tablesLabel = new Label("Tables:");
        tablesLabel.setFont(smallFont);
        tablesLabel.setLabelFor(tablesField);
        tablesLabel.setAlignment(Pos.CENTER_RIGHT);

        tablesField = new TextField();
        tablesField.setPromptText("Number of tables");

        tablesBox.getChildren().addAll(tablesLabel, tablesField);

        HBox ratingBox = new HBox();
        ratingBox.setAlignment(Pos.BOTTOM_RIGHT);
        ratingBox.setSpacing(10);

        ratingLabel = new Label("Rating:");
        ratingLabel.setFont(smallFont);
        ratingLabel.setLabelFor(ratingField);
        ratingLabel.setAlignment(Pos.CENTER_RIGHT);

        ratingField = new TextField();
        ratingField.setPromptText("1.0 - 5.0");

        ratingBox.getChildren().addAll(ratingLabel, ratingField);

        VBox addressBox = new VBox();
        addressBox.setAlignment(Pos.CENTER_RIGHT);
        addressBox.setSpacing(10);

        addressLabel = new Label("Address:");
        addressLabel.setFont(smallFont);
        addressLabel.setLabelFor(addressField);
        addressLabel.setAlignment(Pos.CENTER);

        addressField = new TextArea();
        addressField.setPromptText("Restaurant address");
        addressField.setPrefRowCount(3);
        addressField.setPrefColumnCount(20);
        addressBox.getChildren().addAll(addressLabel, addressField);

        HBox establishedBox = new HBox();
        establishedLabel = new Label("Established");
        establishedField = new DatePicker();
        establishedBox.getChildren().addAll(establishedLabel, establishedField);

        HBox wheelchairAccessibleBox = new HBox();
        wheelchairAccessibleLabel = new Label("Wheelchair Accessible");
        wheelchairAccessibleField = new CheckBox();
        wheelchairAccessibleBox.getChildren().addAll(wheelchairAccessibleLabel, wheelchairAccessibleField);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
        buttonBox.getChildren().addAll(saveButton, cancelButton);

        VBox vContainerLeft = new VBox();
        vContainerLeft.setAlignment(Pos.CENTER);
        vContainerLeft.setSpacing(10);
        vContainerLeft.getChildren().addAll(nameBox,phoneNumberBox, tablesBox, ratingBox, establishedBox);

        VBox vContainerRight = new VBox();
        vContainerRight.setAlignment(Pos.CENTER);
        vContainerRight.setSpacing(10);
        vContainerRight.getChildren().addAll(cuisineBox, addressBox, wheelchairAccessibleBox);

        HBox container = new HBox();
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        container.getChildren().addAll(vContainerLeft, vContainerRight);


        mainPanel.getChildren()
                .addAll(
                        titleBox, container, buttonBox
                );

        root = mainPanel;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public TextField getNameField() {
        return nameField;
    }

    public Label getCuisineLabel() {
        return cuisineLabel;
    }

    public ComboBox<String> getCuisineField() {
        return cuisineField;
    }

    public Label getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public Label getAddressLabel() {
        return addressLabel;
    }

    public TextArea getAddressField() {
        return addressField;
    }

    public Label getTablesLabel() {
        return tablesLabel;
    }

    public TextField getTablesField() {
        return tablesField;
    }

    public Label getRatingLabel() {
        return ratingLabel;
    }

    public TextField getRatingField() {
        return ratingField;
    }

    public Label getEstablishedLabel() {
        return establishedLabel;
    }

    public DatePicker getEstablishedField() {
        return establishedField;
    }

    public Label getWheelchairAccessibleLabel() {
        return wheelchairAccessibleLabel;
    }

    public CheckBox getWheelchairAccessibleField() {
        return wheelchairAccessibleField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
