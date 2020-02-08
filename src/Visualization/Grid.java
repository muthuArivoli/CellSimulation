package Visualization;

import cellsociety.Cell;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Grid {
    public static final int GRID_SIZE = 300;
    public static final int GRID_TOP_LEFT = 110;

    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private ArrayList<ArrayList<Cell>> grid;

    public Grid(Collection array){
        grid = new ArrayList<ArrayList<Cell>>(array);
    }

    public ArrayList<ArrayList<Cell>> getGrid(){
        return grid;
    }

    public List<Rectangle> placeCells(Group root, Collection newGraph) {
        grid = new ArrayList<ArrayList<Cell>>(newGraph);
        ArrayList<Rectangle> display = new ArrayList<Rectangle>();
        int length = grid.size();
        int width = GRID_SIZE/length;
        for (int i=0; i<length; i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(GRID_TOP_LEFT + width*j, GRID_TOP_LEFT + width*i, width, width);
                currentRect.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
                        determineAndSendRectangleThatWasPushed(currentRect);
                    }
                });
                Paint color = row.get(j).getState().getColor();
                currentRect.setFill(color);
                display.add(currentRect);
                root.getChildren().add(currentRect);
            }
        }
        return display;
    }

    private void determineAndSendRectangleThatWasPushed(Rectangle currentRect) {
        System.out.println("");
        int x = (int)(currentRect.getX());
        int y = (int)(currentRect.getY());
        x = (x - GRID_TOP_LEFT)/(GRID_SIZE/grid.size());
        y = (y - GRID_TOP_LEFT)/(GRID_SIZE/grid.size());
        System.out.println(x);
        System.out.println(y);
        //tell the simulation which ones were clicked!
        System.out.println("");
    }
}

