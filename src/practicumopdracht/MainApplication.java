package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.RestaurantPhoneBookController;
import practicumopdracht.data.*;

public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 640;
    private final int HEIGHT = 580;
    private static Stage stage;
    private final static RestaurantPhoneBookDAO restaurantPhoneBookDAO = new TextRestaurantPhoneBookDAO();
    private final static RestaurantContactDAO restaurantContactDAO = new TextRestaurantContactDAO();

    @Override
    public void start(Stage stage) {
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        if (!restaurantPhoneBookDAO.load()) {
            System.err.println("Something went wrong while loading the restaurant phone books!");
        }
        if (!restaurantContactDAO.load()) {
            System.err.println("Something went wrong while loading the restaurant contacts!");
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        MainApplication.stage = stage;

        switchController(new RestaurantPhoneBookController());

        MainApplication.stage.show();
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
    }

    public static RestaurantPhoneBookDAO getRestaurantPhoneBookDAO() {
        return restaurantPhoneBookDAO;
    }

    public static RestaurantContactDAO getRestaurantContactDAO() {
        return restaurantContactDAO;
    }
}
