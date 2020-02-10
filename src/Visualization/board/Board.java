package Visualization.board;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
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
    protected ArrayList<ArrayList<Cell>> grid;

    public Board(){}

    public ArrayList<ArrayList<Cell>> getGrid(){
        return grid;
    }

    public abstract List<Rectangle> placeCells(Group root, Collection newGraph);

    public void updateTotal(Cell cell) {
        numStates.putIfAbsent(cell.getState(), 0.0);
        numStates.put(cell.getState(), numStates.get(cell.getState()) + 1.0);
    }

    public List getPossibleStates(){
        return new ArrayList(numStates.keySet());
    }

    public void determineWhichClicked(Rectangle currentRect) {
        double x = (currentRect.getX());
        double y = (currentRect.getY());
        x = (x - X_START_POS)/(GRID_SIZE/grid.size());
        y = (y - Y_START_POS)/(GRID_SIZE/grid.size());
        //tell the simulation which ones were clicked!
    }

    public Map getNumStates() {
        return numStates;
    }
}

