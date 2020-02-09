package Visualization;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.*;

public class Grid {
    public static final double SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final double SCREEN_HEIGHT = (int) Screen.getPrimary().getBounds().getHeight();
    public static final double GRID_SIZE = SCREEN_HEIGHT/2.0;
    public static final double X_START_POS = SCREEN_WIDTH/2.0;
    public static final double Y_START_POS = SCREEN_WIDTH/15.0;


    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private Map<CellState, Double> numStates;
    private ArrayList<ArrayList<Cell>> grid;

    public Grid(Collection array){
        grid = new ArrayList<ArrayList<Cell>>(array);
        numStates = new HashMap<>();
    }

    public ArrayList<ArrayList<Cell>> getGrid(){
        return grid;
    }

    public List<Rectangle> placeCells(Group root, Collection newGraph) {
        grid = new ArrayList<ArrayList<Cell>>(newGraph);
        ArrayList<Rectangle> display = new ArrayList<Rectangle>();
        int length = grid.size();
        double width = GRID_SIZE/length;
        for (int i=0; i<length; i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(X_START_POS + width*j, Y_START_POS + width*i, width, width);
                currentRect.setOnMouseClicked(event -> handle(currentRect, event));
                updateTotal(row.get(j));
                Paint color = row.get(j).getState().getColor();
                currentRect.setFill(color);
                display.add(currentRect);
                root.getChildren().add(currentRect);
            }
        }
        return display;
    }

    private void updateTotal(Cell cell) {
        numStates.putIfAbsent(cell.getState(), 0.0);
        numStates.put(cell.getState(), numStates.get(cell.getState()) + 1.0);
    }

    public List getPossibleStates(){
        return new ArrayList(numStates.keySet());
    }

    private void determineAndSendRectangleThatWasPushed(Rectangle currentRect) {
        double x = (currentRect.getX());
        double y = (currentRect.getY());
        x = (x - X_START_POS)/(GRID_SIZE/grid.size());
        y = (y - Y_START_POS)/(GRID_SIZE/grid.size());
        //tell the simulation which ones were clicked!
    }

    private void handle(Rectangle currentRect, MouseEvent t) {
        determineAndSendRectangleThatWasPushed(currentRect);
    }

    public Map getNumStates() {
        return numStates;
    }
}

