package cellsociety;


import cellsociety.cellstate.CellState;

import simulation.Clonable;

import java.util.List;

public abstract class Cell implements Clonable {

    protected CellState state;
    protected List<CellState> possibleStates;

    public Cell(CellState state){
        this.state = state;
    }


    public Cell(Cell c){
        state = c.getState();
    }


    public CellState getState(){
        return state;
    }

    public void cycleState(){
        int dex = possibleStates.indexOf(this.state);
        this.state = possibleStates.get((dex + 1)%possibleStates.size());
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override

    public String toString(){
        return state.toString();
    }

    public CellState getNextState(){
        return state;
    }
    public abstract Cell copy();

}
