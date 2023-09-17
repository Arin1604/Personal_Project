package indy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * It's time for Indy! This is the main class to get things started.
 *
 * Class comments here...
 *
 */

public class App extends Application {


    /**
     * This method instantiates the PaneOrganizer and sets up the scene
     * Further, it sets the stage and gets the stage to show up.
     */

    @Override
    public void start(Stage stage) {


        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT );
        stage.setTitle("C(S15)ONTRA");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch(args); // launch is a method inherited from Application
    }
}
