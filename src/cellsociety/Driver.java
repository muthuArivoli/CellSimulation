package cellsociety;

import configuration.Configuration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.FireSimulation;
import simulation.Simulation;

public class Driver extends Application {
    public static final String TITLE = "Simulation";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private boolean waiting;
    private boolean simulating;

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage){
        waiting = true;
        Configuration config = new Configuration();
        myStage.setScene(config.getConfigurationScene());
        myStage.setTitle(TITLE);
        myStage.show();

        while(!config.isCheckSelected()){}

        Simulation sim = new FireSimulation(config.getCurrentParam());

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private void step (double elapsedTime) {
    }

}
