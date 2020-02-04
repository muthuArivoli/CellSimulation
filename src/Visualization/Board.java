package Visualization;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Map;

public class Board {
    public static final int SIZE = 500;
    public static final int STARTING_X = 200;
    public static final int STARTING_Y = 100;

    private Map<String, String> rulesRelatingConditionOfCellToColor;
    private String cellStatus;
    private Group visualizationRoot;
    private ArrayList<Rectangle> myCells;

    public Board(Group root, Map<String, String> rules, String status){
        visualizationRoot = root;
        rulesRelatingConditionOfCellToColor = rules;
        cellStatus = status;
        myCells = new ArrayList<Rectangle>();
        printCurrentStuff();
        addCellToBoard();
    }

    private void addCellToBoard() {
        int length = (int) Math.sqrt(cellStatus.length());
        int width = SIZE/length;
        //check here for double to int
        for (int i=0; i<cellStatus.length(); i++) {
            Rectangle currentRect = new Rectangle(STARTING_X+(width*(i%length)), STARTING_Y+(width*(i/length)), width, width);
            Paint color = Color.BLACK;
            String currentCellValue = cellStatus.substring(i, i+1);
            color = color.valueOf(rulesRelatingConditionOfCellToColor.get(currentCellValue));
            Paint currentColor = Color.BLACK;
            currentRect.setFill(color);
            myCells.add(currentRect);
            visualizationRoot.getChildren().add(currentRect);
        }
    }

    private void deleteOldCellsFromBoard(){
        for (int i=0; i<myCells.size(); i++) {
            visualizationRoot.getChildren().remove(myCells.get(i));
        }
    }

    public void updateBoard(String newStatus){
        deleteOldCellsFromBoard();
        cellStatus = newStatus;
        addCellToBoard();
        printCurrentStuff();
    }

    private void printCurrentStuff(){
        System.out.println("");
        int length = (int) Math.sqrt(cellStatus.length());
        for (int i=0; i<cellStatus.length(); i++) {
            if (i%length==0){
                System.out.println("");
            }
            System.out.print(cellStatus.substring(i, i+1));
        }
        System.out.println("");
        for (Map.Entry<String,String> entry : rulesRelatingConditionOfCellToColor.entrySet()){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }
}
