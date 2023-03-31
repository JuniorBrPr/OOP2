package practicumopdracht.views;

import javafx.scene.Parent;

public abstract class View {

    private final Parent root;

    public View() {
        this.root = initializeView();
    }

    protected abstract Parent initializeView();

    public Parent getRoot() {
        return root;
    }
}
