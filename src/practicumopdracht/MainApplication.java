package practicumopdracht;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.RestaurantPhoneBookController;
import practicumopdracht.data.BinaryRestaurantPhoneBookDAO;
import practicumopdracht.data.ObjectRestaurantContactDAO;
import practicumopdracht.data.RestaurantContactDAO;
import practicumopdracht.data.RestaurantPhoneBookDAO;

/**
 * Main class for the application.
 */
public class MainApplication extends Application {
    private final String TITLE = String.format("Practicumopdracht OOP2 - %s", Main.studentNaam);
    private final int WIDTH = 840;
    private final int HEIGHT = 580;
    private static Stage stage;
    private final static RestaurantPhoneBookDAO restaurantPhoneBookDAO = new BinaryRestaurantPhoneBookDAO();
    private final static RestaurantContactDAO restaurantContactDAO = new ObjectRestaurantContactDAO();

    /**
     * Starts the application.
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class," +
                    " niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        stage.setTitle(TITLE);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        MainApplication.stage = stage;

        switchController(new RestaurantPhoneBookController());

        MainApplication.stage.show();
    }

    /**
     * Switches the controller.
     *
     * @param controller Controller
     */
    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
    }

    /**
     * Gets the RestaurantPhoneBookDAO object which is used to save and load RestaurantPhoneBook objects.
     *
     * @return RestaurantPhoneBookDAO object.
     */
    public static RestaurantPhoneBookDAO getRestaurantPhoneBookDAO() {
        return restaurantPhoneBookDAO;
    }

    /**
     * Gets the RestaurantContactDAO object which is used to save and load RestaurantContact objects.
     *
     * @return RestaurantContactDAO object.
     */
    public static RestaurantContactDAO getRestaurantContactDAO() {
        return restaurantContactDAO;
    }
}
