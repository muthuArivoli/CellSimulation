package configuration;

import Visualization.GUITools;
import cellsociety.Cell;
import configuration.configurationerror.IncorrectFileTypeError;
import configuration.parameters.Parameter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import simulation.*;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

public class Configuration {
    public static final Paint BACKGROUND = Color.WHEAT;
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    private static final Paint TEXT_COLOR = Color.DARKSLATEGRAY;
    private static final double HALFWAY = SCREEN_WIDTH/2.0;
    private static final int FONT_SIZE = 30;
    private static final double INDENT = 100.0;
    private static final double BUTTON_LENGTH = 200.0;

    private static final String DEFAULT_FIRE = "./resources/DefaultFire.xml";
    private static final String DEFAULT_PERCOLATION = "./resources/DefaultPercolation.xml";
    private static final String DEFAULT_SEGREGATION = "./resources/DefaultSegregation.xml";
    private static final String DEFAULT_GAMEOFLIFE = "./resources/DefaultGameofLife.xml";
    private static final String DEFAULT_WATOR = "./resources/DefaultWaTor.xml";
    private static final String DEFAULT_RESOURCES_PACKAGE = "configuration.ConfigurationProperties";

    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE);

    private Scene myScene;
    private Group myLayout;
    private GridBuilder gridBuilder;
    private Parameter currentParam;
    private List<List<Cell>> initialGrid;
    private Simulation currentSim;
    private boolean checkSelected;
    private boolean waiting;

    public Configuration(){
        myLayout = new Group();
        myScene = new Scene(myLayout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        gridBuilder = new GridBuilder();
        checkSelected = false;
        waiting = true;
        initializeConfigurationUI();
    }


    public boolean isSimulationSelected(){ return checkSelected; }

    public Scene getConfigurationScene(){
        return myScene;
    }

    public Simulation getCurrentSim() { return currentSim; }

    private void initializeConfigurationUI(){

        GUITools constructor = new GUITools();

        Text title = constructor.makeText(myResources.getString("LETSSIMULATE"), myResources.getString("Font"), FONT_SIZE, TEXT_COLOR,
                HALFWAY - INDENT - 15, myScene.getHeight()*(1.0/8.0));
        myLayout.getChildren().add(title);

        makeSimulationButtons(constructor);

        Button uploadFile = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(7.0/8.0),
                myResources.getString("Chooseafiletoupload"), BUTTON_LENGTH, myResources.getString("Color6"));
        uploadFile.setOnAction( event -> chooseFile());
        myLayout.getChildren().add(uploadFile);

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }

    private void makeSimulationButtons(GUITools constructor) {
        Button PercolationSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(2.0/8.0),
                myResources.getString("PERCOLATION"), BUTTON_LENGTH, myResources.getString("Color1"));
        PercolationSimulation.setOnAction( event -> uploadPercolation());
        myLayout.getChildren().add(PercolationSimulation);

        Button WaTorSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(3.0/8.0),
                myResources.getString("WATOR"), BUTTON_LENGTH, myResources.getString("Color2"));
        WaTorSimulation.setOnAction( event -> uploadWaTor());
        myLayout.getChildren().add(WaTorSimulation);

        Button GameofLifeSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(4.0/8.0),
                myResources.getString("GAMEOFLIFE"), BUTTON_LENGTH, myResources.getString("Color3"));
        GameofLifeSimulation.setOnAction( event -> uploadGameofLife());
        myLayout.getChildren().add(GameofLifeSimulation);

        Button SegregationSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(5.0/8.0),
                myResources.getString("SEGREGATION"), BUTTON_LENGTH, myResources.getString("Color4"));
        SegregationSimulation.setOnAction( event -> uploadSegregation());
        myLayout.getChildren().add(SegregationSimulation);

        Button FireSimulation = constructor.makeButtons(HALFWAY - INDENT, myScene.getHeight()*(6.0/8.0),
                myResources.getString("FIRE"), BUTTON_LENGTH, myResources.getString("Color5"));

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

    private void initializeFile(File file) throws IncorrectFileTypeError{
        checkSelected = true;
        try{
            currentParam = gridBuilder.makeParameter(file);
        }
        catch(IncorrectFileTypeError e){
            try{
                currentParam = gridBuilder.makeParameter(new File(DEFAULT_FIRE));
            }
            catch(IncorrectFileTypeError ex){
                throw new IncorrectFileTypeError("File could not be configured properly");
            }
        }
        initialGrid = gridBuilder.makeGrid(currentParam);
        createSimulation();
    }

    private void createSimulation(){
       currentSim = currentParam.makeSimulation(this.initialGrid, this.currentParam);
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ESCAPE){
            System.exit(0);
        }
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void notWaiting() {
        waiting = false;
    }
}
