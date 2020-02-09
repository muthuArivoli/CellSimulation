package Visualization;

import cellsociety.Cell;
import configuration.GridBuilder;
import javafx.scene.Group;
import javafx.scene.Node;
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

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Visualization {
    public static final String TITLE = "Visualization";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final int MILLISECOND_DELAY = 1000*100000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final String BUTTON_NAME_PATH = "./resources/ButtonNames.txt";
    public static final String SIMULATION_FILE_EXAMPLE_PATH = "./resources/SimulationFileExample.txt";
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final String DISPLAY = "Display Graph";
    private static final String HIDE = "Hide Graph";



    private Scene myScene;
    private Group root;
    private List<List<Cell>> currentGrid;
    private Grid grid;
    private List<Rectangle> display;
    private Slider mySlider;
    private List<Text> myText;
    private Text simulationSpeedText;
    private GUITools uiBuilder;
    private BarGraph barChart;
    private boolean ready;
    private boolean paused;
    private boolean step;
    private boolean reset;

    public Visualization(Simulation simulation){
        paused = false;
        reset = false;

        uiBuilder = new GUITools();
        GridBuilder gridBuilder = new GridBuilder();

        root = new Group();
        display = new ArrayList<Rectangle>();
        currentGrid = new ArrayList<List<Cell>>();

        currentGrid = gridBuilder.reconstructGrid(simulation.returnGraph());
        grid = new Grid(simulation.returnGraph());

        barChart = new BarGraph(grid.getPossibleStates());

        setupGame(SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        updateGrid(grid.getGrid());
        mySlider = new Slider();
    }

    private void setupGame(double width, double height, Paint background) {
        String[] buttonNames = getButtonNames();

        Button displayGraph = uiBuilder.makeButtons(width * (0.5 / 4), height * (9.0 / 10), DISPLAY, width/10.0, "White");
        displayGraph.setOnAction(value -> displayGraphFunc(displayGraph));

        Button pauseResume = uiBuilder.makeButtons(width * (2.0 / 4), height * (9.0 / 10), PAUSE, width/10.0, "White");
        pauseResume.setOnAction(value -> pauseResumeFunc(pauseResume));

        Button makeStep = uiBuilder.makeButtons(width * (2.5 / 4), height * (9.0 / 10), buttonNames[1], width/10.0, "White");
        makeStep.setOnAction(value -> stepButtonFunc());

        Button changeSimulation = uiBuilder.makeButtons(width * (3.0 / 4), height * (9.0 / 10), buttonNames[3], width/10.0, "White");
        changeSimulation.setOnAction(value -> changeSimulationFunc());

        root.getChildren().addAll(pauseResume, makeStep, changeSimulation);

        simulationSpeedText = uiBuilder.makeText("Simulation Rate: 50", "Serif", 15, Color.BLACK, width*(.4/5), height*(9.22/10));

        root.getChildren().add(simulationSpeedText);

        myScene = new Scene(root, width, height, background);
    }

    private void displayGraphFunc(Button displayGraph) {
        if (displayGraph.getText().equals(DISPLAY)) {
            displayGraph.setText(HIDE);
            handleGraphDisplay(DISPLAY);
        } else {
            displayGraph.setText(RESUME);
            handleGraphDisplay(HIDE);
        }
    }

    private void handleGraphDisplay(String toDo) {
        if(toDo.equals(HIDE)){
            root.getChildren().remove(barChart.getBarGraph());
        }
        else{
            barChart = new BarGraph(grid.getPossibleStates());
            BarChart bc = barChart.getBarGraph();
            bc.setLayoutX(SCREEN_WIDTH*(0.5/4));
            bc.setLayoutY(SCREEN_HEIGHT*(2.0/4));
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
        barChart.updateChart(grid.getNumStates());
        ready = true;
    }

    private void pauseResumeFunc(Button pauseResumeButton) {
        paused = !paused;
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
        step = true;
    }

    private void getFileButtonHasBeenPushed() {
        //make way to get real path
        SimulationFile mySimulationFile = new SimulationFile(SIMULATION_FILE_EXAMPLE_PATH);
        createArrayOfTextFromSimulationFile(mySimulationFile);
        displayArrayOfTextInScene();
        Map<String, String> rulesRelatingConditionOfCellToColor = mySimulationFile.getRulesRelatingConditionOfCellToColor();
        String cellStatus = mySimulationFile.getCellStatus();
    }

    private void changeSimulationFunc() {
        reset = true;
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

    public boolean checkReset() {
        return reset;
    }
}

