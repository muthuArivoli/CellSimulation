package Visualization.shape;

import javafx.scene.shape.Polygon;

/**
 * Triangle is one types of cell shape
 */
public class Triangle {
    private Polygon polygon;
    private double mySize;
    private double pointX;
    private double pointY;
    private double rightX;
    private double rightY;
    private double leftX;
    private double leftY;

    /**
     * @param startX is the starting x
     * @param startY is the starting y
     * @param size is the size
     * @param flip is boolean representing flip
     */
    public Triangle(Double startX, Double startY, Double size, boolean flip){
        mySize = size;
        polygon = new Polygon();
        generatePoints(startX, startY, size, flip);
        if(flip){
            polygon.getPoints().addAll(
                    leftX, leftY,
                    rightX, rightY,
                    pointX, pointY
            );
        }
        else{
            polygon.getPoints().addAll(
                    pointX, pointY,
                    rightX, rightY,
                    leftX, leftY
            );
        }
    }
    private void generatePoints(Double startX, Double startY, Double size, boolean flip){
        Double diff = Math.sqrt(size*size*(3.0/4));
        if(flip){
            leftX = startX - size/2;
            leftY = startY;
            rightX = startX + size/2;
            rightY = startY;
            pointX = startX;
            pointY = startY - size;
        }
        else{
            pointX = startX;
            pointY = startY;
            rightX = startX + size/2;
            rightY = startY - size;
            leftX = startX - size/2;
            leftY = startY - size;
        }
    }

    /**
     * @return the Triangle
     */
    public Polygon getPolygon(){
        return polygon;
    }

    /**
     * @return the size of the Triangle
     */
    public double getSize(){
        return mySize;
    }
}
