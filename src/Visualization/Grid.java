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
    public static final int SIZE = 500;
    public static final int STARTING_X = 200;
    public static final int STARTING_Y = 100;

    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private String cellStatus;
    private Group visualizationRoot;
    private ArrayList<ArrayList<Cell>> grid;

//    public Grid(Group root, Map<String, String> rules, String status, Collection array){

    public Grid(Group root, Collection array){
//        rulesRelatingConditionOfCellToColor = rules;
//        cellStatus = status;
//        printCurrentStuff();
        visualizationRoot = root;
        displayCells();
        grid = new ArrayList<ArrayList<Cell>>(array);
    }

    private void displayCells() {
        int length = (int) Math.sqrt(cellStatus.length());
        int width = SIZE/length;
        for (int i=0; i<grid.size(); i++) {
            ArrayList<Cell> row = grid.get(i);
            for(int j = 0; j < row.size(); j++) {
                Rectangle currentRect = new Rectangle(STARTING_X + (width * (j % length)), STARTING_Y + (width * (i / length)), width, width);
                Paint color = Color.BLACK;
                String currentCellValue = cellStatus.substring(i, i + 1);
//                color = color.valueOf(rulesRelatingConditionOfCellToColor.get(currentCellValue));
                currentRect.setFill(color);
                visualizationRoot.getChildren().add(currentRect);
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

