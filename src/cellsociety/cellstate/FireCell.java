package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireCell extends Cell {

    private CellState state;

    public FireCell(CellState state){
        this.state = state;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.BURNING, State.ALIVE, State.EMPTY));
    }

    public CellState getState(){
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public Cell clone() {
        return new FireCell(this.state);
    }

    @Override
    public String toString(){
        return state.toString();
    }
}
