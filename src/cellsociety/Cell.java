package cellsociety;

import cellsociety.cellstate.CellState;
import simulation.Clonable;

public abstract class Cell implements Clonable {

    protected CellState state;

    public Cell(){}

    public CellState getState(){
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public abstract Cell clone();

    @Override
    public String toString(){
        return state.toString();
    }
}
