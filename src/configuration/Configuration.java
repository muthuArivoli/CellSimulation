package configuration;

import cellsociety.Cell;
import configuration.parameters.Parameter;
import configuration.parameters.Fire;
import configuration.parameters.Percolation;
import configuration.parameters.PredatorPrey;
import configuration.parameters.Segregation;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Configuration {
    public static final Paint BACKGROUND = Color.LIGHTGOLDENRODYELLOW;
    public static final double SCREEN_WIDTH = 600.0;
    public static final double SCREEN_HEIGHT = 600.0;
    private static final Paint TEXT_COLOR = Color.BLACK;
    private static final ArrayList<Parameter> possible_simulations = new ArrayList<Parameter>(Arrays.asList(new Fire(),
            new Percolation(), new PredatorPrey(), new Segregation()));

    private Scene myScene;
    private Group myLayout;
    private GridBuilder gridBuilder;
    private Parameter currentParam;
    private ArrayList<ArrayList<Cell>> initialGrid;

    public Configuration(){
        initializeConfiguration();
    }

    private void initializeConfiguration(){
        myScene = new Scene(myLayout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);
        myLayout = new Group();
        gridBuilder = new GridBuilder();

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(possible_simulations));
        myLayout.getChildren().add(combo_box);

        Button uploadFile = new Button("Choose a file to upload");
        uploadFile.setOnAction( event -> chooseFile());
        myLayout.getChildren().add(uploadFile);

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        File file = fileChooser.showOpenDialog(fileStage);
        if(file != null){
            createParameter(file);
            createGrid(file);
        }
    }

    private void createParameter(File file){
        currentParam = gridBuilder.makeParameter(file);
    }

    private void createGrid(File file){
        initialGrid = gridBuilder.makeGrid(file);
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ESCAPE){
            System.exit(0);
        }
    }

    public Scene getConfigurationScene(){
        return myScene;
    }

    public Collection getInitialGrid() {
        return initialGrid;
    }

    public Parameter getCurrentParam() {
        return currentParam;
    }
}
