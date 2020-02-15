package Visualization.board;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;

import java.util.*;

/**
 * Board represents an abstract class for all the types of boards the simulation can use
 */
public abstract class Board {
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final double GRID_SIZE = SCREEN_HEIGHT;
    public static final double X_START_POS = SCREEN_WIDTH - SCREEN_HEIGHT;
    public static final double Y_START_POS = 0;

    protected Map<String, String> rulesRelatingConditionOfCellToColor;
    protected Map<CellState, Double> numStates;
    protected List<List<Cell>> grid;
    protected List<Shape> display;

    /**
     * @return the grid of cells
     */
    public List<List<Cell>> getGrid(){
        return grid;
    }

    /**
     * @param root location of where to put board
     * @param newGraph The collection of the new placement of cells
     */
    public abstract List<Shape> placeCells(Group root, Collection newGraph);

    /**
     * @param cell changes the condition of the cell
     */
    public void updateTotal(Cell cell) {
        numStates.putIfAbsent(cell.getState(), 0.0);
        numStates.put(cell.getState(), numStates.get(cell.getState()) + 1.0);
    }

    /**
     * @return numStates as an arrayList
     */
    public List getPossibleStates(){
        return new ArrayList(numStates.keySet());
    }

    /**
     * @param shape the shape that is being clicked
     * contains procedure for when shape is clicked
     */
    public void handleShapeClicked(Shape shape) {
        int displayIndex = display.indexOf(shape);
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

    /**
     * @return the numStates as a map
     */
    public Map getNumStates() {
        return numStates;
    }
}

