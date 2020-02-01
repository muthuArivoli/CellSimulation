package configuration;


import configuration.parameters.Parameter;
import configuration.parameters.Fire;
import configuration.parameters.Percolation;
import configuration.parameters.PredatorPrey;
import configuration.parameters.Segregation;
import configuration.parameters.PredatorPrey;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Configuration {
    public static final String TITLE = "Simulation";
    public static final Paint BACKGROUND = Color.LIGHTGOLDENRODYELLOW;
    public static final double SCREEN_WIDTH = 600.0;
    public static final double SCREEN_HEIGHT = 600.0;
    private static final Paint TEXT_COLOR = Color.BLACK;
    private static final ArrayList<Parameter> possible_simulations = new ArrayList<Parameter>(Arrays.asList(new Fire(),
            new Percolation(), new PredatorPrey(), new Segregation()));

    private Stage myStage;
    private Scene myScene;
    private BorderPane myLayout;

    public Configuration(){
        myStage = new Stage();
        myLayout = new BorderPane();
        myScene = new Scene(myLayout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(possible_simulations));
        Button uploadFile = new Button("Choose a file to upload");
        uploadFile.setOnAction( event -> chooseFile());

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();

    }

    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(myStage);
        if(file != null){
            openFile(file);
        }
    }

    private void openFile(File file){
        System.out.println("hello");
    }


    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ESCAPE){
            System.exit(0);
        }
    }

}
