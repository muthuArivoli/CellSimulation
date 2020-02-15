package Visualization.shape;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Hexagon is one types of cell shape
 */
public class Hexagon {
    private Polygon polygon;
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
    private double width;
    private double height;

    /**
     * @param startX is the starting x
     * @param startY is the starting y
     * @param size is the size
     */
    public Hexagon(Double startX, Double startY, Double size){
        polygon = new Polygon();
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

    private void generatePoints(Double startX, Double startY, Double length){
        Double size = length/2.0;
        Double halfHeight = size*Math.sqrt(3);

        Double diff = length/4.0;
        width = size + diff;
        height = 2*halfHeight;

        topLeftX = startX;
        topLeftY = startY;
        topRightX = startX + size;
        topRightY = startY;
        midRightX = startX + size + diff;
        midRightY = startY - halfHeight;
        bottomRightX = startX + size;
        bottomRightY = startY - 2*halfHeight;
        bottomLeftX = startX;
        bottomLeftY = startY - 2*halfHeight;
        midLeftX = startX - diff;
        midLeftY = startY - halfHeight;
    }

    /**
     * @return the hexagon
     */
    public Polygon getPolygon(){
        return polygon;
    }

    /**
     * @return the width of the hexagon
     */
    public double getXOffset(){
        return width;
    }

    /**
     * @return the Y offset of the hexagon
     */
    public double getYOffset(){ return height/2.0;}

}