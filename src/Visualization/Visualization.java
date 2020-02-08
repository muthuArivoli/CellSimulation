package Visualization;

import cellsociety.Cell;
import cellsociety.Driver;
import configuration.GridBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import simulation.Simulation;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Visualization {
    public static final int SIZE = 500;
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final String BUTTON_NAME_PATH = "./resources/ButtonNames.txt";
    public static final String SIMULATION_FILE_EXAMPLE_PATH = "./resources/SimulationFileExample.txt";
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";

    private Scene myScene;
    private Group root;
    private List<List<Cell>> currentGrid;
    private Grid grid;
    private List<Rectangle> display;
    private Slider mySlider;
    private Text simulationSpeedText;
    private GUITools uiBuilder;

    public Visualization(Simulation simulation){
        root = new Group();
        uiBuilder = new GUITools();
        display = new ArrayList<Rectangle>();
        GridBuilder gridBuilder = new GridBuilder();
        currentGrid = new ArrayList<List<Cell>>();
        currentGrid = gridBuilder.reconstructGrid(simulation.returnGraph());
        grid = new Grid(simulation.returnGraph());
        setupGame(SIZE, SIZE, BACKGROUND);
        mySlider = new Slider();
    }

    private void setupGame(int width, int height, Paint background) {
        ButtonNames myButtonNames = new ButtonNames(BUTTON_NAME_PATH);
        String[] buttonNames = myButtonNames.getButtonNames();

        Button pauseResume = uiBuilder.makeButtons(SIZE * (1.8 / 4), SIZE * (9.0 / 10), PAUSE, 25, "White");
        pauseResume.setOnAction(value -> pauseResumeFunc(pauseResume));

        Button makeStep = uiBuilder.makeButtons(SIZE * (2.35 / 4), SIZE * (9.0 / 10), buttonNames[1], 25, "White");
        makeStep.setOnAction(value -> stepButtonFunc());

        Button getFile = uiBuilder.makeButtons(SIZE * (2.8 / 4), SIZE * (9.0 / 10), buttonNames[2], 25, "White");
        getFile.setOnAction(value -> getFileButtonHasBeenPushed());

        Button changeSimulation = uiBuilder.makeButtons(SIZE * (0.1 / 4), SIZE * (9.0 / 10), buttonNames[3], 25, "White");
        changeSimulation.setOnAction(value -> changeSimulationFunc());

        root.getChildren().addAll(pauseResume, makeStep, getFile, changeSimulation);


        simulationSpeedText = uiBuilder.makeText("Simulation Rate: 50", "Serif", 15, Color.BLACK, SIZE*(.39/5), SIZE*(8.8/10));

        root.getChildren().add(simulationSpeedText);

        myScene = new Scene(root, width, height, background);
    }


    public Scene getScene(){
        return myScene;
    }

    public void updateGrid(Collection graph) {
        for(Rectangle r : display){
            root.getChildren().remove(r);
        }
        display.clear();
        display = grid.placeCells(root, graph);
    }

    private void pauseResumeFunc(Button pauseResumeButton) {
        if (pauseResumeButton.getText().equals(RESUME)) {
            pauseResumeButton.setText(PAUSE);
            mySlider.setSimulationSpeed(true);
            System.out.println(mySlider.getCurrentSimulationSpeed());
        } else {
            pauseResumeButton.setText(RESUME);
            mySlider.setSimulationSpeed(false);
            System.out.println(mySlider.getCurrentSimulationSpeed());
        }
    }

    public int getCurrentSimulationSpeed(){
        if (mySlider.getCurrentSimulationSpeed()==0){
            return 10000;
        }
        return 1;
    }

    private void stepButtonFunc(){
        System.out.println("step");
    }

    private void getFileButtonHasBeenPushed() {
        SimulationFile mySimulationFile = new SimulationFile(SIMULATION_FILE_EXAMPLE_PATH);
        mySimulationFile.addArrayOfTextToScreen(root);
    }

    private void changeSimulationFunc() {
        System.out.println("return to main screen");
    }

}
