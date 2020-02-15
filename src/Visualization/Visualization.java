package Visualization;


import Visualization.board.Board;
import Visualization.board.HexagonalBoard;
import Visualization.board.RectangularBoard;
import Visualization.board.TriangularBoard;
import configuration.GridBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import simulation.Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Visualization represents the visualization of the simulation
 */
public class Visualization {
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final Paint BACKGROUND = Color.WHEAT;

    private Scene myScene;
    private Group root;
    private Board grid;
    private List<Shape> display;
    private Slider mySlider;
    private Text simulationSpeedText;
    private GUITools uiBuilder;
    private BarGraph barChart;
    private boolean ready;
    private boolean paused;
    private boolean step;
    private boolean newSimulation;
    private boolean showGraph;
    private String[] myButtonNames;
    private String[] myShapes;


    /**
     * This is the constructor for the visualization
     * @param simulation The simulation that the visualization is representing
     * creates a new instance of GUITools, group, and slider
     */
    public Visualization(Simulation simulation){
        paused = false;
        newSimulation = false;
        showGraph = false;

        uiBuilder = new GUITools();
        GridBuilder gridBuilder = new GridBuilder();

        root = new Group();
        display = new ArrayList<Shape>();

        Shapes newShapes = new Shapes();
        myShapes = newShapes.getMyShapes();

        initializeGrid(simulation);

        barChart = new BarGraph(grid.getNumStates());

        setupGame(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        updateGrid(grid.getGrid());
        mySlider = new Slider();
    }

    private void initializeGrid(Simulation simulation) {
        String gridType = simulation.getGridType();

        if (gridType.equals(myShapes[0])){
            grid = new HexagonalBoard(simulation.returnGraph());
        }
        else if(gridType.equals(myShapes[1])){
            grid = new TriangularBoard(simulation.returnGraph());
        }
        else{
            grid = new RectangularBoard(simulation.returnGraph());
        }
    }

    private void setupGame(double width, double height, Paint background) {
        ButtonNames myButtons = new ButtonNames();
        myButtonNames = myButtons.getButtonNames();

        Button displayGraph = uiBuilder.makeButtons(width * (0.5 / 4), height * (2.0/10), myButtonNames[0], width/10.0, "White");
        displayGraph.setOnAction(value -> displayGraphFunc(displayGraph));

        Button pauseResume = uiBuilder.makeButtons(width * (1.0 / 4), height * (2.0 / 10), myButtonNames[2], width/10.0, "White");
        pauseResume.setOnAction(value -> pauseResumeFunc(pauseResume));

        Button makeStep = uiBuilder.makeButtons(width * (0.5 / 4), height * (9.0 / 10), myButtonNames[4], width/10.0, "White");
        makeStep.setOnAction(value -> stepButtonFunc());

        Button changeSimulation = uiBuilder.makeButtons(width * (1.0 / 4), height * (9.0 / 10), myButtonNames[5], width/10.0, "White");
        changeSimulation.setOnAction(value -> changeSimulationFunc());

        root.getChildren().addAll(displayGraph, pauseResume, makeStep, changeSimulation);

        String simulationSpeedTextString = barChart.getSimulationSpeedText();
        simulationSpeedText = uiBuilder.makeText(simulationSpeedTextString, "Serif", 15, Color.BLACK, width*(0.5/4), height*(1.0/10));

        root.getChildren().add(simulationSpeedText);


        myScene = new Scene(root, width, height, background);
    }


    private void displayGraphFunc(Button displayGraph) {
        if (displayGraph.getText().equals(myButtonNames[0])) {
            displayGraph.setText(myButtonNames[1]);
            showGraph = true;
            handleGraphDisplay();
        } else {
            displayGraph.setText(myButtonNames[0]);
            showGraph = false;
            handleGraphDisplay();
        }
    }

    private void handleGraphDisplay() {
        if(!showGraph){
            root.getChildren().remove(barChart.getBarGraph());
        }
        else{
            root.getChildren().remove(barChart.getBarGraph());
            barChart = new BarGraph(grid.getNumStates());
            BarChart bc = barChart.getBarGraph();
            bc.setLayoutX(0);
            bc.setLayoutY(SCREEN_HEIGHT*(1.0/4));
            root.getChildren().add(barChart.getBarGraph());
        }
    }

    /**
     * @return returns the scene
     */
    public Scene getScene(){
        return myScene;
    }

    /**
     * @graph the new graph to be displayed
     * updates the visualization with the new graph
     */
    public void updateGrid(Collection graph) {
        for(Shape s : display){
            root.getChildren().remove(s);
        }
        display.clear();
        display = grid.placeCells(root, graph);
        handleGraphDisplay();
        ready = true;
    }

    private void pauseResumeFunc(Button pauseResumeButton) {
        paused = !paused;
        if (pauseResumeButton.getText().equals(myButtonNames[3])) {
            pauseResumeButton.setText(myButtonNames[2]);
            mySlider.setSimulationSpeed(true);
        } else {
            pauseResumeButton.setText(myButtonNames[3]);
            mySlider.setSimulationSpeed(false);
        }
    }

    /**
     * @return returns the simulation speed
     */
    public int getCurrentSimulationSpeed(){
        if (mySlider.getCurrentSimulationSpeed()==0){
            return 10000;
        }
        return 1;
    }

    private void stepButtonFunc(){
        step = true;
    }

    private void changeSimulationFunc() {
        newSimulation = true;
    }

    /**
     * @return boolean relating to if the visualization is ready
     */
    public boolean isVisualizationReady() {
        return ready;
    }

    /**
     * @return boolean relating to if the visualization is paused
     */
    public boolean checkPaused() {
        return paused;
    }

    /**
     * @return boolean relating to if the simulation needs to be steped
     */
    public boolean stepped() {
        return step;
    }

    /**
     * sets the step back to false
     */
    public void setStep(){
        step = false;
    }

    /**
     * @return boolean relating to if the simulation needs to be changed
     */
    public boolean checkStartNewSim() {
        return newSimulation;
    }

    /**
     * @return boolean relating to if the new simulation has started
     */
    public void newSimStarted() {
        newSimulation = false;
    }
}


