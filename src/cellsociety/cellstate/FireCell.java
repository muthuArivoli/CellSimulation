package cellsociety.cellstate;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FireCell extends Cell {

    public FireCell(CellState state){
        super(state);
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.BURNING, State.ALIVE, State.EMPTY));
    }


    @Override
    public Cell clone() {
        return new FireCell(this.state);
    }

}
