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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import simulation.Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

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


    private Scene myScene;
    private Group root;
    private ArrayList<ArrayList<Cell>> currentGrid;
    private Grid grid;
    private ArrayList<Rectangle> display;
    private Slider mySlider;
    private int simulationSpeed;
    private SimulationFile mySimulationFile;
    private ArrayList<Text> myText;
    private Board myBoard;
    private Text simulationSpeedText;


    public Visualization(Simulation simulation){
        root = new Group();
        simulationSpeed = 50;
        display = new ArrayList<Rectangle>();
        GridBuilder builder = new GridBuilder();
        currentGrid = builder.reconstructGrid(simulation.returnGraph());
        grid = new Grid(simulation.returnGraph());
        setupGame(SIZE, SIZE, BACKGROUND);
        mySlider = new Slider(simulationSpeed);
    }

    public Scene getScene(){
        return myScene;
    }

    public void setCells(ArrayList<ArrayList<Cell>> grid){
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

    private Button makeChangeSimulationSpeedButton(String name) {
        Button changeSimulationSpeedButton = new Button(name);
        changeSimulationSpeedButton.setTranslateX(SIZE * (1.0 / 4));
        changeSimulationSpeedButton.setTranslateY(SIZE * (9.0 / 10));

        changeSimulationSpeedButton.setOnAction(value -> {
            //watch for multiple windows
            //display the slider
        });
        return changeSimulationSpeedButton;
    }

    private void setupGame(int width, int height, Paint background) {
        String[] buttonNames = getButtonNames();
        Button a = makePauseResumeButton(buttonNames[0]);
        Button b = makeStepButton(buttonNames[1]);
        Button c = makeGetFileButton(buttonNames[2]);
        Button d = makeChangeSimulationSpeedButton(buttonNames[3]);
        root.getChildren().addAll(a,b,c,d);
        setCells(grid.getGrid());
        simulationSpeedText = new Text(SIZE*(0.4/5), (SIZE * (9.22 / 10)), "Simulation Rate: 50");
        simulationSpeedText.setFill(Color.BLACK);
        simulationSpeedText.setFont(Font.font(java.awt.Font.SERIF, 15));
        root.getChildren().add(simulationSpeedText);

        myScene = new Scene(root, width, height, background);
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
            e.printStackTrace();
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
        setCells(grid.getGrid());
    }

    public int getVisualizationsSpeed(){
        return mySlider.getCurrentSimulationSpeed();
    }

    private Button makePauseResumeButton(String name) {
        String firstName = name.substring(0, name.indexOf("/"));
        String lastName = name.substring(name.indexOf("/")+1);

        Button pauseResumeButton = new Button(firstName);
        pauseResumeButton.setTranslateX(SIZE * (2.0 / 4));
        pauseResumeButton.setTranslateY(SIZE * (9.0 / 10));

        pauseResumeButton.setOnAction(value -> {
            if (pauseResumeButton.getText().equals(firstName)) {
                pauseResumeButton.setText(lastName);
                simulationSpeed = 0;
                System.out.println(simulationSpeed);
                //Pause simulation
            } else {
                pauseResumeButton.setText(firstName);
                simulationSpeed = mySlider.getCurrentSimulationSpeed();
                System.out.println(simulationSpeed);
                //Resume simulation
            }
        });

        return pauseResumeButton;
    }

    private Button makeStepButton(String name) {
        Button stepButton = new Button(name);
        stepButton.setTranslateX(SIZE * (2.35 / 4));
        stepButton.setTranslateY(SIZE * (9.0 / 10));

        stepButton.setOnAction(value -> {
            //get new status
            try {
                myBoard.updateBoard("102201200");
            }
            catch(Exception e) {
                System.out.println("No File Loaded");
            }
        });
        return stepButton;
    }

    private Button makeGetFileButton(String name) {
        Button getFileButton = new Button(name);
        getFileButton.setTranslateX(SIZE * (2.6 / 4));
        getFileButton.setTranslateY(SIZE * (9.0 / 10));

        getFileButton.setOnAction(value -> {
            getFileButtonHasBeenPushed();
        });
        return getFileButton;
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

        ArrayList<String> arrayOfRules = mySimulationFile.getArrayOfRules();

        for (int i=0; i<arrayOfRules.size(); i++) {
            currentText = new Text(25, 25, arrayOfRules.get(i));
            currentText.setFill(Color.BLACK);
            currentText.setFont(Font.font(java.awt.Font.SERIF, 15));
            myText.add(currentText);
        }
    }
}
