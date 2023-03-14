package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import practicumopdracht.models.RestaurantPhoneBook;

import static java.lang.Integer.MAX_VALUE;

public class RestaurantPhoneBookView extends View {

    @Override
    protected void initializeView() {
        Font smallFont = Font.font("Helvetica", FontWeight.BLACK, 16);
        Font largeFont = Font.font("Helvetica", FontWeight.BOLD, 36);

        Label titleLabel = new Label("Restaurant Phone Book");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(largeFont);

        HBox titleBox = new HBox();
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
                )));
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().add(titleLabel);
        TextField nameField = new TextField();
        nameField.setPromptText("Restaurant Name");

        Label nameLabel = new Label("Name:");
        nameLabel.setLabelFor(nameField);
        nameLabel.setAlignment(Pos.CENTER_LEFT);

        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(nameLabel, nameField);

        TextField cuisineField = new TextField();
        cuisineField.setPromptText("Cuisine");
        cuisineField.setPrefWidth(MAX_VALUE);

        Label cuisineLabel = new Label("Cuisine:");
        cuisineLabel.setLabelFor(cuisineField);

        HBox cuisineBox = new HBox();
        cuisineBox.getChildren().addAll(cuisineLabel, cuisineField);

        TextField ratingField = new TextField();
        ratingField.setPromptText("Restaurant Rating (0-10.0))");
        ratingField.setPrefWidth(MAX_VALUE);

        Label ratingLabel = new Label("Rating:");
        ratingLabel.setLabelFor(ratingField);

        HBox ratingBox = new HBox();
        ratingBox.getChildren().addAll(ratingLabel, ratingField);

        TextField tablesField = new TextField();
        tablesField.setPromptText("City");

        Label tablesLabel = new Label("Tables:");
        tablesLabel.setLabelFor(tablesField);

        HBox tablesBox = new HBox();
        tablesBox.getChildren().addAll(tablesLabel, tablesField);

        DatePicker establishedField = new DatePicker();
        establishedField.setPromptText("Date of establishment");

        Label establishedLabel = new Label("Established:");
        establishedLabel.setLabelFor(establishedField);

        HBox establishedBox = new HBox();
        establishedBox.getChildren().addAll(establishedLabel, establishedField);

        CheckBox wheelchairAccessibleField = new CheckBox();

        Label wheelchairAccessibleLabel = new Label("Wheelchair Accessible:");
        wheelchairAccessibleLabel.setLabelFor(wheelchairAccessibleField);

        HBox wheelchairAccessibleBox = new HBox();
        wheelchairAccessibleBox.getChildren().addAll(wheelchairAccessibleLabel, wheelchairAccessibleField);

        GridPane restaurantDetailsGrid = new GridPane();
        restaurantDetailsGrid.setHgap(10);
        restaurantDetailsGrid.setVgap(10);
        restaurantDetailsGrid.setPadding(new Insets(10));
        restaurantDetailsGrid.add(nameBox, 0, 0);
        restaurantDetailsGrid.add(cuisineBox, 1, 0);
        restaurantDetailsGrid.add(tablesBox, 0, 1);
        restaurantDetailsGrid.add(ratingBox, 1, 1);
        restaurantDetailsGrid.add(establishedBox, 0, 2);
        restaurantDetailsGrid.add(wheelchairAccessibleBox, 1, 2);

        restaurantDetailsGrid.getChildren().forEach(node -> {
            if (node instanceof HBox) {
                ((HBox) node).setSpacing(10);
                ((HBox) node).setAlignment(Pos.CENTER_LEFT);

                ((HBox) node).getChildren().forEach(child -> {
                    if (child instanceof Label) {
                        ((Label) child).setMinWidth(100);
                        ((Label) child).setFont(smallFont);
                        ((Label) child).setAlignment(Pos.CENTER_LEFT);
                    }
                    if (child instanceof TextField) {
                        ((TextField) child).setMinWidth(130);
                        ((TextField) child).setPrefWidth(MAX_VALUE);
                    }
                    if (child instanceof DatePicker) {
                        ((DatePicker) child).setMinWidth(100);
                        ((DatePicker) child).setPrefWidth(MAX_VALUE);
                    }
                    if (child instanceof CheckBox) {
                        ((CheckBox) child).setMinWidth(50);
                    }
                });
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setFont(smallFont);
        saveButton.setPrefWidth(MAX_VALUE);

        ListView<RestaurantPhoneBook> restaurantPhoneBookList = new ListView<>();
        restaurantPhoneBookList.setPlaceholder(new Label("No restaurant phone books added yet"));

        Button newButton = new Button("New");
        newButton.setFont(smallFont);
        newButton.setPrefWidth(MAX_VALUE);

        Button deleteButton = new Button("Delete");
        deleteButton.setFont(smallFont);
        deleteButton.setPrefWidth(MAX_VALUE);

        HBox listButtonBox = new HBox();
        listButtonBox.setSpacing(10);
        listButtonBox.getChildren().addAll(deleteButton, newButton);

        VBox listContainer = new VBox();
        listContainer.getChildren().addAll(restaurantPhoneBookList, listButtonBox);

        Button selectButton = new Button("Select");
        selectButton.setFont(smallFont);
        selectButton.setPrefWidth(MAX_VALUE);

        VBox mainPanel = new VBox();
        mainPanel.setSpacing(10);
        mainPanel.setAlignment(Pos.CENTER);
        mainPanel.setPadding(new Insets(10));
        mainPanel.getChildren().addAll(titleBox, restaurantDetailsGrid,
                saveButton, listContainer, selectButton);

        root = mainPanel;
    }
}
