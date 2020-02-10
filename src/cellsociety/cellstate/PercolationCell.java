package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PercolationCell extends Cell {

    public PercolationCell(CellState state){
        super(state);
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.OPEN, State.BLOCKED, State.FULL));
    }

    @Override
    public Cell copy() {
        return new FireCell(this.state);
    }
}
