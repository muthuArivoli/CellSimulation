package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLifeCell extends Cell {

    private CellState state;
    private List<CellState> possibleStates;

    public GameOfLifeCell(CellState state){
        super(state);
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.ALIVE, State.DEAD, State.EMPTY));
    }



    @Override
    public Cell clone() {
        return new GameOfLifeCell(this.state);
    }

}
