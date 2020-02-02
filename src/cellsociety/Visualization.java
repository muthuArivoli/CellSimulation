package cellsociety;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Visualization extends Application {
    public static final String TITLE = "Visualization";
    public static final int SIZE = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;

    private Scene myScene;


    @Override
    public void start (Stage stage) {
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Button makePauseResumeButton(){
        Button pauseResumeButton = new Button("PAUSE");
        pauseResumeButton.setTranslateX(SIZE*(3.0/4));
        pauseResumeButton.setTranslateY(SIZE*(9.0/10));

        pauseResumeButton.setOnAction(value -> {
            if (pauseResumeButton.getText().equals("PAUSE")){
                pauseResumeButton.setText("RESUME");
                //Pause simulation
            }
            else{
                pauseResumeButton.setText("PAUSE");
                //Resume simulation
            }
        });

        return pauseResumeButton;
    }

    private Button makeStepButton(){
        Button stepButton = new Button("STEP");
        stepButton.setTranslateX(SIZE*(3.0/4));
        stepButton.setTranslateY(SIZE*(9.0/10));

        stepButton.setOnAction(value -> {
            //step through once

            Slider mySlider = new Slider();
        });
        return stepButton;
    }

    private Button makeGetFileButton(){
        Button getFileButton = new Button("GET FILE");
        getFileButton.setTranslateX(SIZE*(1.0/8));
        getFileButton.setTranslateY(SIZE*(2.0/10));

        getFileButton.setOnAction(value -> {
            //let user pick

     });
        return getFileButton;
    }

    private Scene setupGame (int width, int height, Paint background) {
        Button a = makePauseResumeButton();
        Button b = makeStepButton();
        Button c = makeGetFileButton();

        JSlider mySlider = new JSlider();
        HBox hbox = new HBox(a, b, c);


        Scene scene = new Scene(hbox, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void step (double elapsedTime) {
    }

    private void handleKeyInput (KeyCode code) {
        /**
        if (code == KeyCode.Q) {
            duplicateBouncers();
        }
        else if (code == KeyCode.W) {
            handleW();
        }
        else if (code == KeyCode.E) {
            handleE();
        }
         **/
    }

    public static void Visualization (String[] args) {
        launch(args);
    }
}
