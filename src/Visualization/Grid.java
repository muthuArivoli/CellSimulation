package Visualization;

import cellsociety.Cell;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Grid {
    public static final int SIZE = 800;
    public static final int STARTING_X = 50;
    public static final int STARTING_Y = 50;

    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private String cellStatus;
    private Group visualizationRoot;
    private ArrayList<ArrayList<Cell>> grid;

//    public Grid(Group root, Map<String, String> rules, String status, Collection array){

    public Grid(Collection array){
        grid = new ArrayList<ArrayList<Cell>>(array);
    }

    public ArrayList<ArrayList<Cell>> getGrid(){
        return grid;
    }

    public void displayCells(Group root) {
        int length = grid.size();
        int width = grid.get(0).size();
        for (int i=0; i<grid.size(); i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(100 + 15*j, 100 + 15*i, width, length);
                Paint color = Color.RED;
                currentRect.setFill(color);
                root.getChildren().add(currentRect);
            }
        }
    }

    private void printCurrentStuff(){
        for (Map.Entry<String,String> entry : rulesRelatingConditionOfCellToColor.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        System.out.println(cellStatus);
    }
}

