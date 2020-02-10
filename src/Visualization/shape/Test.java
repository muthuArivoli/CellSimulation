package Visualization.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Test extends Application {


    double topLeftX;
    double topLeftY;
    double topRightX;
    double topRightY;
    double midRightX;
    double midRightY;
    double bottomRightX;
    double bottomRightY;
    double bottomLeftX;
    double bottomLeftY;
    double midLeftX;
    double midLeftY;

    private void generatePoints(Double startX, Double startY, Double size){
        Double diff = Math.sqrt(size*size*(3.0/4));
        System.out.println(diff);
        topLeftX = startX;
        topLeftY = startY;
        topRightX = startX + size;
        topRightY = startY;
        midRightX = startX + size + diff;
        midRightY = startY - size/2;
        bottomRightX = startX + size;
        bottomRightY = startY - size;
        bottomLeftX = startX;
        bottomLeftY = startY - size;
        midLeftX = startX - diff;
        midLeftY = startY - size/2;
    }

    @Override
    public void start(Stage stage) {
        //Creating a Polygon
        Polygon polygon = new Polygon();

        generatePoints(300.0,50.0, 50.0);

        //Adding coordinates to the polygon
        polygon.getPoints().addAll(
                topLeftX, topLeftY,
                topRightX, topRightY,
                midRightX, midRightY,
                bottomRightX, bottomRightY,
                bottomLeftX, bottomLeftY,
                midLeftX, midLeftY
        );

        //Creating a Group object
        Group root = new Group(polygon);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Drawing a Polygon");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}