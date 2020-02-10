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

import java.util.ArrayList;
import java.util.List;

public class Driver extends Application {
    public static final String TITLE = "Simulation";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 100000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private boolean waiting;

    private List<Stage> myStages;
    private List<Scene> myScenes;
    private List<Configuration> myConfigs;
    private List<Simulation> mySimulations;
    private List<Visualization> myVisualizations;
    private int speed;

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage currentStage){
        myStages = new ArrayList<Stage>();
        myScenes = new ArrayList<Scene>();
        myConfigs = new ArrayList<Configuration>();
        mySimulations = new ArrayList<Simulation>();
        myVisualizations = new ArrayList<Visualization>();

        waiting = true;
        speed = 1;

        Configuration myConfig = new Configuration();
        myConfigs.add(myConfig);

        myStages.add(currentStage);
        myScenes.add(myConfig.getConfigurationScene());

        for(int i = 0; i < myStages.size(); i++){
            myStages.get(i).setScene(myScenes.get(i));
            myStages.get(i).setTitle(TITLE);
            myStages.get(i).show();
        }

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    private void step (double elapsedTime) {
        for(Configuration c : myConfigs){
            if(c.isWaiting()) {
                if (c.isSimulationSelected()) {
                    c.notWaiting();
                    Simulation mySimulation = c.getCurrentSim();
                    mySimulations.add(mySimulation);
                    myVisualizations.add(new Visualization(mySimulation));
                    for (int i = 0; i < myVisualizations.size(); i++) {
                        myStages.get(i).setScene(myVisualizations.get(i).getScene());
                    }
                }
            }
        }
        for(int j = 0; j < myVisualizations.size(); j++){
            Visualization v = myVisualizations.get(j);
            Simulation sim = mySimulations.get(j);
            if(v.checkStartNewSim()) {
                v.newSimStarted();
                Configuration myConfig = new Configuration();
                myConfigs.add(myConfig);
                Stage myStage = new Stage();
                Scene myScene = myConfig.getConfigurationScene();
                myStage.setScene(myScene);
                myStage.show();
                myStages.add(myStage);
            }
            if(!v.checkPaused()){
                if (v.isVisualizationReady()) {
                    sim.update();
                    v.updateGrid(sim.returnGraph());
                    speed = v.getCurrentSimulationSpeed();
                    System.out.println(speed);
                }
            }
            else if(v.stepped()) {
                sim.update();
                v.updateGrid(sim.returnGraph());
                v.setStep();
            }
        }

    }
}

