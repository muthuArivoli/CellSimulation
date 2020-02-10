package Visualization.shape;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Hexagon extends Shape {
    private double topLeftX;
    private double topLeftY;
    private double topRightX;
    private double topRightY;
    private double midRightX;
    private double midRightY;
    private double bottomRightX;
    private double bottomRightY;
    private double bottomLeftX;
    private double bottomLeftY;
    private double midLeftX;
    private double midLeftY;

    public Hexagon(Double startX, Double startY, Double size){
        Polygon polygon = new Polygon();
        generatePoints(startX, startY, size);
        polygon.getPoints().addAll(
                topLeftX, topLeftY,
                topRightX, topRightY,
                midRightX, midRightY,
                bottomRightX, bottomRightY,
                bottomLeftX, bottomLeftY,
                midLeftX, midLeftY
        );
    }

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

}