package practicumopdracht.views;

import javafx.scene.Parent;

/**
 * Abstract class for views.
 */
public abstract class View {

    private final Parent root;

    public View() {
        this.root = initializeView();
    }

    /**
     * Initializes the view.
     *
     * @return Parent
     */
    protected abstract Parent initializeView();

    /**
     * Gets the root of the view.
     *
     * @return Parent
     */
    public Parent getRoot() {
        return root;
    }
}
