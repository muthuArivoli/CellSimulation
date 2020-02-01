package configuration;

import configuration.parameters.Parameter;
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
import javafx.stage.Stage;
import java.util.ArrayList;


public class Configuration {
    public static final String TITLE = "Simulation";
    public static final Paint BACKGROUND = Color.LIGHTGOLDENRODYELLOW;
    public static final double SCREEN_WIDTH = 600.0;
    public static final double SCREEN_HEIGHT = 600.0;
    private static final Paint TEXT_COLOR = Color.DEEPPINK;
    private static final String RESOURCE_LOCATION = "/resources/";
    private static final ArrayList<Parameter> possible_simulations = new ArrayList<>

    private Stage myStage;
    private Scene myScene;
    private BorderPane myLayout;

    public Configuration(){
        myStage = new Stage();
        myLayout = new BorderPane();
        myScene = new Scene(myLayout, SCREEN_WIDTH, SCREEN_HEIGHT, BACKGROUND);

        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(possible_simulations));
        Button uploadFile = new Button();

        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();

    }



    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ESCAPE){
            System.exit(0);
        }
    }

}
