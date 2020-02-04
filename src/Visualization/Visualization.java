package Visualization;

import cellsociety.Cell;
import configuration.GUITools;
import configuration.GridBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Collection;

public class Visualization {
    public static final String TITLE = "Visualization";
    public static final int SIZE = 500;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;
    private Group root;
    private ArrayList<ArrayList<Cell>> currentGrid;
    private Grid grid;

    public Visualization(Simulation simulation){
        root = new Group();
        GridBuilder builder = new GridBuilder();
        currentGrid = builder.reconstructGrid(simulation.returnGraph().graphToCollection());
        grid = new Grid(simulation.returnGraph().graphToCollection());
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
    }

    public Scene getScene(){
        return myScene;
    }

    public void setCells(ArrayList<ArrayList<Cell>> grid){
        int length = grid.size();
        int width = 50;
        for (int i=0; i<length; i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(100 + 50*j, 100 + 15*i, width, length);
                Paint color = Color.RED;
                currentRect.setFill(color);
                root.getChildren().add(currentRect);
            }
        }
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
            Slider mySlider = new Slider(50);
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

        root.getChildren().addAll(a,b,c,d);

        setCells(grid.getGrid());

        Scene scene = new Scene(root, width, height, background);
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

    public void updateGrid(Collection graph) {
        grid = new Grid(graph);
        setCells(grid.getGrid());

    }
}
