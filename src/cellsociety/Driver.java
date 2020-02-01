package cellsociety;

import configuration.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Driver extends Application {
    public static final String TITLE = "Simulation";

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage){
        Configuration config = new Configuration();
        myStage.setScene(config.getConfigurationScene());
        myStage.show();
    }
}
