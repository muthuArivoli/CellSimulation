package Visualization.board;

import cellsociety.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class SquareBoard extends Board {

    public SquareBoard(Collection array){
        grid = new ArrayList<ArrayList<Cell>>(array);
        numStates = new HashMap<>();
    }

    public List<Rectangle> placeCells(Group root, Collection newGraph) {
        grid = new ArrayList<ArrayList<Cell>>(newGraph);
        numStates = new HashMap<>();
        ArrayList<Rectangle> display = new ArrayList<Rectangle>();
        int length = grid.size();
        double width = GRID_SIZE/length;
        for (int i=0; i<length; i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(X_START_POS + width*j, Y_START_POS + width*i, width, width);
                currentRect.setOnMouseClicked(event -> this.determineWhichClicked(currentRect));
                updateTotal(row.get(j));
                Paint color = row.get(j).getState().getColor();
                currentRect.setFill(color);
                display.add(currentRect);
                root.getChildren().add(currentRect);
            }
        }
        return display;
    }

}
