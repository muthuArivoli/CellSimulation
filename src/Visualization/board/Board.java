package Visualization.board;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;

import java.util.*;

public abstract class  Board {
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final double GRID_SIZE = SCREEN_HEIGHT;
    public static final double X_START_POS = SCREEN_WIDTH - SCREEN_HEIGHT;
    public static final double Y_START_POS = 0;

    protected Map<String, String> rulesRelatingConditionOfCellToColor;
    protected Map<CellState, Double> numStates;
    protected List<ArrayList<Cell>> grid;
    protected List<Shape> display;

    public Board(){}

    public List<ArrayList<Cell>> getGrid(){
        return grid;
    }

    public abstract List<Shape> placeCells(Group root, Collection newGraph);

    public void updateTotal(Cell cell) {
        numStates.putIfAbsent(cell.getState(), 0.0);
        numStates.put(cell.getState(), numStates.get(cell.getState()) + 1.0);
    }

    public List getPossibleStates(){
        return new ArrayList(numStates.keySet());
    }

    public void handleShapeClicked(Shape shape) {
        int displayIndex = display.indexOf(shape);
        System.out.println("PRINTING INDICES");
        System.out.println(displayIndex);
        int x_index = 0;
        int y_index = 0;
        if(displayIndex != 0){
            x_index = displayIndex%(grid.size());
            y_index = (int) grid.size()/displayIndex;
        }
        Cell clickedCell = grid.get(y_index).get(x_index);
        clickedCell.cycleState();
        Shape clickedShape = display.get(displayIndex);
        clickedShape.setFill(clickedCell.getState().getColor());
    }

    public Map getNumStates() {
        return numStates;
    }
}

