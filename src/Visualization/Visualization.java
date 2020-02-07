package Visualization;

import cellsociety.Cell;
import configuration.GridBuilder;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public static final String TITLE = "Visualization";
    public static final int SIZE = 500;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000*100000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final String BUTTON_NAME_PATH = "./resources/ButtonNames.txt";
    public static final String SIMULATION_FILE_EXAMPLE_PATH = "./resources/SimulationFileExample.txt";
    public static final int GRID_SIZE = 300;
    public static final int GRID_TOP_LEFT = 100;
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";


    private Scene myScene;
    private Group root;
    private List<List<Cell>> currentGrid;
    private Grid grid;
    private List<Rectangle> display;
    private Slider mySlider;
    private int simulationSpeed;
    private SimulationFile mySimulationFile;
    private List<Text> myText;
    private Board myBoard;
    private Text simulationSpeedText;
    private GUITools uiBuilder;


    public Visualization(Simulation simulation){
        root = new Group();
        uiBuilder = new GUITools();
        simulationSpeed = 50;
        display = new ArrayList<Rectangle>();
        GridBuilder gridBuilder = new GridBuilder();
        currentGrid = new ArrayList<List<Cell>>();
        currentGrid = gridBuilder.reconstructGrid(simulation.returnGraph());
        grid = new Grid(simulation.returnGraph());
        setupGame(SIZE, SIZE, BACKGROUND);
        mySlider = new Slider(simulationSpeed);
    }

    private void setupGame(int width, int height, Paint background) {
        String[] buttonNames = getButtonNames();

        Button pauseResume = uiBuilder.makeButtons(SIZE * (2.0 / 4), SIZE * (9.0 / 10), PAUSE, 25, "White");
        pauseResume.setOnAction(value -> pauseResumeFunc(pauseResume));

        Button makeStep = uiBuilder.makeButtons(SIZE * (2.35 / 4), SIZE * (9.0 / 10), buttonNames[1], 25, "White");
        makeStep.setOnAction(value -> stepButtonFunc());

        Button getFile = uiBuilder.makeButtons(SIZE * (2.6 / 4), SIZE * (9.0 / 10), buttonNames[2], 25, "White");
        getFile.setOnAction(value -> getFileButtonHasBeenPushed());

        Button changeSimulation = uiBuilder.makeButtons(SIZE * (1.0 / 4), SIZE * (9.0 / 10), buttonNames[3], 25, "White");
        changeSimulation.setOnAction(value -> changeSimulationFunc());

        root.getChildren().addAll(pauseResume, makeStep, getFile, changeSimulation);
        placeCells(grid.getGrid());
        simulationSpeedText = uiBuilder.makeText("Simulation Rate: 50", "Serif", 15, Color.BLACK, SIZE*(.4/5), SIZE*(9.22/10));

        root.getChildren().add(simulationSpeedText);

        myScene = new Scene(root, width, height, background);
    }


    public Scene getScene(){
        return myScene;
    }

    public void placeCells(ArrayList<ArrayList<Cell>> grid){
        int length = grid.size();
        int width = GRID_SIZE/length;
        System.out.println(width);
        for (int i=0; i<length; i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(GRID_TOP_LEFT + width*j, GRID_TOP_LEFT + width*i, width, width);
                Paint color = row.get(j).getState().getColor();
                currentRect.setFill(color);
                display.add(currentRect);
                root.getChildren().add(currentRect);
            }
        }
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
        grid = new Grid(graph);
        placeCells(grid.getGrid());
    }

    private void pauseResumeFunc(Button pauseResumeButton) {
        if (pauseResumeButton.getText().equals(RESUME)) {
            pauseResumeButton.setText(PAUSE);
            simulationSpeed = 0;
            System.out.println(simulationSpeed);
            //Pause simulation
        } else {
            pauseResumeButton.setText(RESUME);
            simulationSpeed = mySlider.getCurrentSimulationSpeed();
            System.out.println(simulationSpeed);
            //Resume simulation
        }
    }

    private void stepButtonFunc(){
        //get new status
        try {
            myBoard.updateBoard("102201200");
        }
        catch(Exception e) {
            System.out.println("No File Loaded");
        }
    }

    private void getFileButtonHasBeenPushed() {
        //make way to get real path
        mySimulationFile = new SimulationFile(SIMULATION_FILE_EXAMPLE_PATH);
        createArrayOfTextFromSimulationFile();
        displayArrayOfTextInScene();
        Map<String, String> rulesRelatingConditionOfCellToColor = mySimulationFile.getRulesRelatingConditionOfCellToColor();
        String cellStatus = mySimulationFile.getCellStatus();
//        myBoard = new Board(root, rulesRelatingConditionOfCellToColor, cellStatus);
    }

    private void changeSimulationFunc() {
        //watch for multiple windows
        mySlider = new Slider(simulationSpeed);
    }


    private void createArrayOfTextFromSimulationFile() {
        myText = new ArrayList<>();
        Text currentText = new Text(25, 25, mySimulationFile.getFileName());
        currentText.setFill(Color.BLACK);
        currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
        myText.add(currentText);
        currentText = new Text(25, 25, mySimulationFile.getSimulationName());
        currentText.setFill(Color.BLACK);
        currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
        myText.add(currentText);

        List<String> arrayOfRules = mySimulationFile.getArrayOfRules();

        for (int i=0; i<arrayOfRules.size(); i++) {
            currentText = new Text(25, 25, arrayOfRules.get(i));
            currentText.setFill(Color.BLACK);
            currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
            myText.add(currentText);
        }
    }
}
