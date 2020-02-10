package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLifeCell extends Cell {

    private CellState state;

    public GameOfLifeCell(CellState state){
        this.state = state;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.ALIVE, State.DEAD, State.EMPTY));
    }

    public CellState getState(){
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public Cell clone() {
        return new GameOfLifeCell(this.state);
    }

    @Override
    public String toString(){
        return state.toString();
    }
}
