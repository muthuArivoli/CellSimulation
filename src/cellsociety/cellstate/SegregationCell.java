package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegregationCell extends Cell {

    private CellState state;
    private List<CellState> possibleStates;

    public SegregationCell(CellState state){
        this.state = state;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.MINORITY, State.MAJORITY, State.EMPTY));
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