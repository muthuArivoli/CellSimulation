package cellsociety;

import Visualization.Visualization;
import configuration.Configuration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.Simulation;

public class Driver extends Application {
    public static final String TITLE = "Simulation";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000*100 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private boolean waiting;

    private Stage myStage;
    private Scene myScene;
    private Configuration myConfig;
    private Simulation mySimulation;
    private Visualization myVisualization;

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage currentStage){
        waiting = true;
        myConfig = new Configuration();
        myStage = currentStage;
        myScene = myConfig.getConfigurationScene();
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private void step (double elapsedTime) {
        if(waiting){
            if(myConfig.isSimulationSelected()){
                waiting = false;
                mySimulation = myConfig.getCurrentSim();
                myVisualization = new Visualization(mySimulation);
                myStage.setScene(myVisualization.getScene());
            }
        }
        else{
            mySimulation.update();
            myVisualization.updateGrid(mySimulation.returnGraph());
        }
    }

}