package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.views.RestaurantContactView;
import practicumopdracht.views.RestaurantPhoneBookView;


public class MainApplication extends Application {
    private String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private int WIDTH = 640;
    private int HEIGHT = 580;

    @Override
    public void start(Stage stage) {
        if(!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        RestaurantContactView restaurantContactView = new RestaurantContactView();
        RestaurantPhoneBookView restaurantPhoneBookView = new RestaurantPhoneBookView();

        stage.setScene(new Scene(restaurantPhoneBookView.getRoot()));
        stage.show();
    }
}
