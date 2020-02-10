package Visualization.board;

import Visualization.shape.Hexagon;
import cellsociety.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HexagonalBoard extends Board {
    public HexagonalBoard(Collection array){
        grid = new ArrayList<ArrayList<Cell>>(array);
        numStates = new HashMap<>();
    }

    public List<Shape> placeCells(Group root, Collection newGraph) {

        grid = new ArrayList<ArrayList<Cell>>(newGraph);
        numStates = new HashMap<>();

        List<Shape> display = new ArrayList<Shape>();

        int length = grid.size();
        double width = GRID_SIZE/length;

        for (int i=0; i<length; i++) {
            List<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Shape shape = new Hexagon(X_START_POS + width*j, Y_START_POS + width*i, width);
                shape.setOnMouseClicked(event -> this.determineWhichClicked(shape));
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
