package Visualization.board;

import Visualization.shape.Triangle;
import cellsociety.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TriangularBoard extends Board {

    public TriangularBoard(Collection array){
        grid = new ArrayList<List<Cell>>(array);
        numStates = new HashMap<>();
    }

    public List<Shape> placeCells(Group root, Collection newGraph) {
        grid = new ArrayList<List<Cell>>(newGraph);
        numStates = new HashMap<>();
        boolean flip = false;
        display = new ArrayList<Shape>();
        int length = grid.size();
        double width = GRID_SIZE/length;
        for (int i=0; i<length; i++) {
            List<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Triangle triangle = new Triangle(X_START_POS + width*j, Y_START_POS + width*i, width, flip);
                flip = !flip;
                Polygon shape = triangle.getPolygon();
                shape.setOnMouseClicked(event -> this.handleShapeClicked(shape));
                updateTotal(row.get(j));
                Paint color = row.get(j).getState().getColor();
                shape.setFill(color);
                display.add(shape);
                root.getChildren().add(shape);
            }
        }
        return display;
    }
}
