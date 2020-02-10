package Visualization.shape;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Triangle extends Shape {
    private double topX;
    private double topY;
    private double rightX;
    private double rightY;
    private double leftX;
    private double leftY;
    public Triangle(Double startX, Double startY, Double size){
        Polygon polygon = new Polygon();
        generatePoints(startX, startY, size);
        polygon.getPoints().addAll(
                topX, topY,
                rightX, rightY,
                leftX, leftY
        );
    }
    private void generatePoints(Double startX, Double startY, Double size){
        Double diff = Math.sqrt(size*size*(3.0/4));
        topX = startX;
        topY = startY;
        rightX = startX + size/2;
        rightY = startY - size;
        leftX = startX - size/2;
        leftY = startY - size;
    }
}
