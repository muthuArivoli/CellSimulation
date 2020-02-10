package cellsociety;


import cellsociety.cellstate.CellState;

import simulation.Clonable;

public abstract class Cell implements Clonable {

    protected CellState state;

    public Cell(){}


    public Cell(Cell c){
        state = c.getState();
    }


    public CellState getState(){
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    @Override

    public String toString(){
        return state.toString();
    }

    public abstract Cell clone();

}
