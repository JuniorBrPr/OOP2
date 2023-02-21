package practicumopdracht.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import practicumopdracht.models.RestaurantPhoneBook;

import static java.lang.Integer.MAX_VALUE;

public class RestaurantPhoneBookView extends View {

    private TextField countryField;
    private TextField cityField;
    private ListView<RestaurantPhoneBook> restaurantPhoneBookList;
    private Button saveButton;
    private Button deleteButton;
    private Button newButton;
    private Button selectButton;

    @Override
    protected void initializeView() {
        VBox mainPanel = new VBox();
        mainPanel.setSpacing(10);
        mainPanel.setAlignment(Pos.CENTER);
        mainPanel.setPadding(new Insets(10));

        Font smallFont = Font.font("Helvetica", FontWeight.BLACK, 16);
        Font largeFont = Font.font("Helvetica", FontWeight.BOLD, 36);

        HBox titleBox = new HBox();
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
                )));
        titleBox.setAlignment(Pos.TOP_CENTER);

        Label titleLabel = new Label("Restaurant Phone Book");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(largeFont);

        titleBox.getChildren().add(titleLabel);

        HBox countryAndCityBox = new HBox();
        countryAndCityBox.setSpacing(10);

        HBox countryBox = new HBox();
        countryBox.setSpacing(10);
        countryBox.setAlignment(Pos.CENTER_LEFT);

        Label countryLabel = new Label("Country:");
        countryLabel.setFont(smallFont);
        countryLabel.setLabelFor(countryField);
        countryLabel.setMinWidth(80);

        countryField = new TextField();
        countryField.setPromptText("Country");
        countryField.setPrefWidth(MAX_VALUE);

        countryBox.getChildren().addAll(countryLabel, countryField);

        HBox cityBox = new HBox();
        cityBox.setSpacing(10);
        cityBox.setAlignment(Pos.CENTER_RIGHT);

        Label cityLabel = new Label("City:");
        cityLabel.setFont(smallFont);
        cityLabel.setLabelFor(cityField);
        cityLabel.setMinWidth(50);

        cityField = new TextField();
        cityField.setPromptText("City");
        cityField.setPrefWidth(MAX_VALUE);

        cityBox.getChildren().addAll(cityLabel, cityField);

        countryAndCityBox.getChildren().addAll(countryBox, cityBox);

        saveButton = new Button("Save");
        saveButton.setFont(smallFont);
        saveButton.setPrefWidth(MAX_VALUE);

        VBox listContainer = new VBox();

        restaurantPhoneBookList = new ListView<>();
        restaurantPhoneBookList.setPlaceholder(new Label("No restaurant phone books added yet"));

        HBox listButtonBox = new HBox();
        listButtonBox.setSpacing(10);

        newButton = new Button("New");
        newButton.setFont(smallFont);
        newButton.setPrefWidth(MAX_VALUE);

        deleteButton = new Button("Delete");
        deleteButton.setFont(smallFont);
        deleteButton.setPrefWidth(MAX_VALUE);

        listButtonBox.getChildren().addAll(deleteButton, newButton);

        listContainer.getChildren().addAll(restaurantPhoneBookList, listButtonBox);

        selectButton = new Button("Select");
        selectButton.setFont(smallFont);
        selectButton.setPrefWidth(MAX_VALUE);

        mainPanel.getChildren().addAll(titleBox, countryAndCityBox, saveButton, listContainer, selectButton);

        root = mainPanel;
    }
}
