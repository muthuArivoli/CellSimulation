package configuration;

import Visualization.GUITools;
import cellsociety.Cell;
import configuration.parameters.Parameter;
import configuration.parameters.FireParameter;
import configuration.parameters.PercolationParameter;
import configuration.parameters.WaTorParameter;
import configuration.parameters.SegregationParameter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import simulation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Configuration {
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final double SCREEN_WIDTH = 600.0;
    public static final double SCREEN_HEIGHT = 600.0;
    private static final Paint TEXT_COLOR = Color.DARKSLATEGRAY;
    private static final ArrayList<Parameter> possible_simulations = new ArrayList<Parameter>(Arrays.asList(new FireParameter(),
            new PercolationParameter(), new WaTorParameter(), new SegregationParameter()));
    private static final double HALFWAY = SCREEN_WIDTH/2.0;
    private static final int FONT_SIZE = 30;
    private static final double INDENT = 100.0;
    private static final double BUTTON_LENGTH = 200.0;

    private static final String DEFAULT_FIRE = "./resources/DefaultFire.xml";
    private static final String DEFAULT_PERCOLATION = "./resources/DefaultPercolation.xml";
    private static final String DEFAULT_SEGREGATION = "./resources/DefaultSegregation.xml";
    private static final String DEFAULT_GAMEOFLIFE = "./resources/DefaultGameofLife.xml";
    private static final String DEFAULT_WATOR = "./resources/DefaultWaTor.xml";

    private Scene myScene;
    private Group myLayout;
    private GridBuilder gridBuilder;
    private Parameter currentParam;
    private ArrayList<ArrayList<Cell>> initialGrid;
    private Simulation currentSim;
    private boolean checkSelected;

    public Configuration(){
        myLayout = new Group();
        myScene = new Scene(myLayout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        gridBuilder = new GridBuilder();
        checkSelected = false;
        initializeConfigurationUI();
    }


    public boolean isSimulationSelected(){ return checkSelected; }

    public Scene getConfigurationScene(){
        return myScene;
    }

    public Collection getInitialGrid() {
        return initialGrid;
    }

    public Parameter getCurrentParam() {
        return currentParam;
    }

    public Simulation getCurrentSim() { return currentSim; }

    private void initializeConfigurationUI(){

        GUITools constructor = new GUITools();

        Text title = constructor.makeText("LETS SIMULATE!", "Times New Roman", FONT_SIZE, TEXT_COLOR,
                HALFWAY - INDENT - 15, myScene.getHeight()*(1.0/8.0));
        myLayout.getChildren().add(title);

        makeSimulationButtons(constructor);

        Button uploadFile = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(7.0/8.0),
                "Choose a file to upload:", BUTTON_LENGTH, "-fx-base: white;");
        uploadFile.setOnAction( event -> chooseFile());
        myLayout.getChildren().add(uploadFile);

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }

    private void makeSimulationButtons(GUITools constructor) {
        Button PercolationSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(2.0/8.0),
                "PERCOLATION", BUTTON_LENGTH, "-fx-base: #264653;");
        PercolationSimulation.setOnAction( event -> uploadPercolation());
        myLayout.getChildren().add(PercolationSimulation);

        Button WaTorSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(3.0/8.0),
                "WATOR", BUTTON_LENGTH, "-fx-base: #2A9D8F;");
        WaTorSimulation.setOnAction( event -> uploadWaTor());
        myLayout.getChildren().add(WaTorSimulation);

        Button GameofLifeSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(4.0/8.0),
                "GAME OF LIFE", BUTTON_LENGTH, "-fx-base: #E9C46A;");
        GameofLifeSimulation.setOnAction( event -> uploadGameofLife());
        myLayout.getChildren().add(GameofLifeSimulation);

        Button SegregationSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(5.0/8.0),
                "SEGREGATION", BUTTON_LENGTH, "-fx-base:  #F4A261;");
        SegregationSimulation.setOnAction( event -> uploadSegregation());
        myLayout.getChildren().add(SegregationSimulation);

        Button FireSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(6.0/8.0),
                "FIRE", BUTTON_LENGTH, "-fx-base: #E76F51;");
        FireSimulation.setOnAction( event -> uploadFire());
        myLayout.getChildren().add(FireSimulation);
    }

    private void uploadFire() {
        initializeFile(new File(DEFAULT_FIRE));
    }

    private void uploadPercolation() {
        initializeFile(new File(DEFAULT_PERCOLATION));
    }

    private void uploadGameofLife() {
        initializeFile(new File(DEFAULT_GAMEOFLIFE));
    }

    private void uploadSegregation() {
        initializeFile(new File(DEFAULT_SEGREGATION));
    }

    private void uploadWaTor() {
        initializeFile(new File(DEFAULT_WATOR));
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        File file = fileChooser.showOpenDialog(fileStage);
        if(file != null){
            initializeFile(file);
        }
    }

    private void initializeFile(File file){
        checkSelected = true;
        currentParam = gridBuilder.makeParameter(file);
        initialGrid = gridBuilder.makeGrid(currentParam);
        createSimulation();
    }

    private void createSimulation(){
        if(currentParam.toString().equals("Fire Simulation")){
            currentSim = new FireSimulation(this.getInitialGrid(), this.getCurrentParam());
        }

        if(currentParam.toString().equals("Game of Life Simulation")){
            currentSim = new GameOfLifeSimulation(this.getInitialGrid());
        }
        if(currentParam.toString().equals("Percolation Simulation")){
            currentSim = new PercolationSimulation(this.getInitialGrid());
        }
        if(currentParam.toString().equals("Segregation Simulation")){
            currentSim = new SegregationSimulation(this.getInitialGrid(), this.getCurrentParam());
        }
        if(currentParam.toString().equals("Wa Tor Simulation")){
            currentSim = new WatorSimulation(this.getInitialGrid());
        }

    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ESCAPE){
            System.exit(0);
        }
    }
}
