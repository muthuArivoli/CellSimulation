package Visualization;

import Visualization.board.Board;
import Visualization.board.HexagonalBoard;
import Visualization.board.SquareBoard;
import Visualization.board.TriangularBoard;
import configuration.GridBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import simulation.Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Visualization {
    public static final String TITLE = "Visualization";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final String BUTTON_NAME_PATH = "./resources/ButtonNames.txt";
    private static final String HEXAGON = "Hexagon";
    private static final String TRIANGULAR = "TRIANGLE";
    private static final String SQUARE = "Square";


    private Scene myScene;
    private Group root;
    private Board grid;
    private List<Rectangle> display;
    private Slider mySlider;
    private List<Text> myText;
    private Text simulationSpeedText;
    private GUITools uiBuilder;
    private BarGraph barChart;
    private boolean ready;
    private boolean paused;
    private boolean step;
    private boolean newSimulation;
    private boolean showGraph;
    private ResourceBundle myResources;
    private String[] myButtonNames;

    public Visualization(Simulation simulation){
        paused = false;
        newSimulation = false;
        showGraph = false;

        uiBuilder = new GUITools();
        GridBuilder gridBuilder = new GridBuilder();

        root = new Group();
        display = new ArrayList<Rectangle>();

        initializeGrid(simulation);

        barChart = new BarGraph(grid.getNumStates());

        setupGame(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        updateGrid(grid.getGrid());
        mySlider = new Slider();
    }

    private void initializeGrid(Simulation simulation) {
        String gridType = simulation.getGridType();
        if(gridType.equals(HEXAGON)){
            grid = new HexagonalBoard(simulation.returnGraph());
        }
        else if(gridType.equals(TRIANGULAR)){
            grid = new TriangularBoard(simulation.returnGraph());
        }
        else{
            grid = new SquareBoard(simulation.returnGraph());
        }
    }

    private void setupGame(double width, double height, Paint background) {
        //String[] buttonNames = getButtonNames();
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

        simulationSpeedText = uiBuilder.makeText("Simulation Rate: 50", "Serif", 15, Color.BLACK, width*(0.5/4), height*(1.0/10));

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


    public Scene getScene(){
        return myScene;
    }

    private String[] getButtonNames() {
        String[] buttonNames = new String[4];
        //change hard coding
        try {
            File newFile = new File(BUTTON_NAME_PATH);
            Scanner myReader = new Scanner(newFile);
            int i=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                buttonNames[i] = data;
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return buttonNames;
    }

    private void displayArrayOfTextInScene(){
        for (int i=0; i<myText.size(); i++) {
            myText.get(i).setY(50+i*50);
            root.getChildren().add(myText.get(i));
        }
    }

    public void updateGrid(Collection graph) {
        for(Rectangle r : display){
            root.getChildren().remove(r);
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
            System.out.println(mySlider.getCurrentSimulationSpeed());
        } else {
            pauseResumeButton.setText(myButtonNames[3]);
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
        step = true;
    }

    private void changeSimulationFunc() {
        newSimulation = true;
    }

    private void createArrayOfTextFromSimulationFile(SimulationFile mySimulationFile) {
        myText = new ArrayList<>();
        createTextBoxWithTheFollowingInformation(mySimulationFile.getFileName());
        createTextBoxWithTheFollowingInformation(mySimulationFile.getSimulationName());
        List<String> arrayOfRules = mySimulationFile.getArrayOfRules();
        for (int i=0; i<arrayOfRules.size(); i++) {
            createTextBoxWithTheFollowingInformation(arrayOfRules.get(i));
        }
    }
    private void createTextBoxWithTheFollowingInformation(String text){
        Text currentText = new Text(25, 25, text);
        currentText.setFill(Color.BLACK);
        currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
        myText.add(currentText);
    }

    public boolean isVisualizationReady() {
        return ready;
    }

    public boolean checkPaused() {
        return paused;
    }

    public boolean stepped() {
        return step;
    }

    public void setStep(){
        step = false;
    }

    public boolean checkStartNewSim() {
        return newSimulation;
    }

    public void newSimStarted() {
        newSimulation = false;
    }
}


