package Visualization;

import cellsociety.Cell;
import configuration.GUITools;
import configuration.GridBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.Simulation;

import java.util.ArrayList;

public class Visualization {
    public static final String TITLE = "Visualization";
    public static final int SIZE = 500;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;
    private ArrayList<ArrayList<Cell>> currentGrid;

    public Visualization(Simulation simulation){
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        GridBuilder builder = new GridBuilder();
        currentGrid = builder.reconstructGrid();
    }

    public Scene getScene(){
        return myScene;
    }

    private Button makeStepButton(){
        Button stepButton = new Button("STEP");
        stepButton.setTranslateX(SIZE*(3.0/4));
        stepButton.setTranslateY(SIZE*(9.0/10));

        stepButton.setOnAction(value -> {
            //step through once
        });
        return stepButton;
    }


    private Button makeGetFileButton(){
        Button getFileButton = new Button("GET FILE");
        getFileButton.setTranslateX(SIZE*(1.0/8));
        getFileButton.setTranslateY(SIZE*(2.0/10));

        getFileButton.setOnAction(value -> {
            //let user pick

     });
        return getFileButton;
    }

    private Button makeChangeSimulationRateButton() {
        Button getFileButton = new Button("Change Simualtion Rate");
        getFileButton.setTranslateX(SIZE*(1.0/8));
        getFileButton.setTranslateY(SIZE*(2.0/10));

        getFileButton.setOnAction(value -> {
            Slider mySlider = new Slider();
            System.out.print(mySlider.getCurrentSimulationSpeed());
        });
        return getFileButton;
    }

    private Scene setupGame (int width, int height, Paint background) {
        GUITools constructor = new GUITools();
        Button a = constructor.makePauseResumeButton(SIZE);

        Button b = makeStepButton();
        Button c = makeGetFileButton();
        Button d = makeChangeSimulationRateButton();

        HBox hbox = new HBox(a, b, c, d);

        Scene scene = new Scene(hbox, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void handleKeyInput (KeyCode code) {
        /**
        if (code == KeyCode.Q) {
            duplicateBouncers();
        }
        else if (code == KeyCode.W) {
            handleW();
        }
        else if (code == KeyCode.E) {
            handleE();
        }
         **/
    }
}
