package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegregationCell extends Cell {

    public SegregationCell(CellState state){
        super(state);
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.MINORITY, State.MAJORITY, State.EMPTY));
    }

    @Override
    public Cell clones() {
        return new FireCell(this.state);
    }

}