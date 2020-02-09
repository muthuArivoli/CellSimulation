package cellsociety.cellstate;

import cellsociety.Cell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatorCell extends Cell {
    private CellState myState;
    private int energy;
    private int birth;
    private List<CellState> possibleStates;

    public WatorCell(CellState myState){
        this.myState = myState;
        energy = 5;
        birth = 7;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.DEAD, State.PREY, State.PREDATOR));
    }

    public int getEnergy(){
        return energy;
    }

    public int getBirth() {
        return birth;
    }
    public void decrementBirth(){
        birth--;
    }
    public void decrementEnergy(){
        energy--;
    }
    public CellState getState(){
        return myState;
    }
    public Paint getColor(){
        return Color.GREEN;
    }

    @Override
    public Cell clone() {
        return new WatorCell(this.state);
    }
}
