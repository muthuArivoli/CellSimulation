package configuration;

import cellsociety.CellStates;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum States implements CellStates {
    EMPTY (Color.GRAY),
    BURNING (Color.RED),
    PREDATOR (Color.BLUE),
    PREY (Color.GREEN),
    DEAD (Color.YELLOW),
    FULL (Color.BLUE),
    OPEN (Color.WHITE),
    BLOCKED (Color.BLACK),
    MAJORITY (Color.PURPLE),
    MINORITY (Color.YELLOW),
    ALIVE (Color.GREEN);

    private Paint color;

    States(Paint color){
        this.color = color;
    }
    public Paint getColor(){
        return color;
    }
}
