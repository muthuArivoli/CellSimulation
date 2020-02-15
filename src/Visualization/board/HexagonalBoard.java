package Visualization.board;

import Visualization.shape.Hexagon;
import cellsociety.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Hexagonal Board is one types of board shape that extends the abstract class Board
 */
public class HexagonalBoard extends Board {

    /**
     * This is the constructor for the Hexagonal Board
     * @param array is the collection of cells
     * initializes variables
     */
    public HexagonalBoard(Collection array){
        grid = new ArrayList<List<Cell>>(array);
        numStates = new HashMap<>();
    }

    /**
     * @param root the group for cells to be placed
     * @param newGraph the collection of cells in the simulation
     * places the cells
     */
    public List<Shape> placeCells(Group root, Collection newGraph) {
        grid = new ArrayList<List<Cell>>(newGraph);
        numStates = new HashMap<>();
        display = new ArrayList<Shape>();
        double width = GRID_SIZE/grid.size();
        double xOffset = calcXOffset(width);
        double yOffset = calcYOffset(width);
        boolean offset = false;
        for (int i=0; i<grid.size(); i++) {
            List<Cell> row = grid.get(i);
            double xPos = X_START_POS;
            if(offset){
                xPos += xOffset;
            }
            for(int j = 0; j < row.size(); j++) {
                Hexagon hex = new Hexagon(xPos + j*(2*xOffset), Y_START_POS + yOffset*i, width);
                Polygon shape = hex.getPolygon();
                shape.setOnMouseClicked(event -> this.handleShapeClicked(shape));
                updateTotal(row.get(j));
                Paint color = row.get(j).getState().getColor();
                shape.setFill(color);
                display.add(shape);
                root.getChildren().add(shape);
            }
            offset = !offset;
        }
        return display;
    }

    private double calcXOffset(Double length){
        return (3.0)*length/(4.0);
    }
    private double calcYOffset(Double length){
        double halfHeight = length*Math.sqrt(3);
        return halfHeight;
    }
}
