package cellsociety;

import configuration.States;
import simulation.Clonable;

public class Cell implements Clonable {

    private CellStates state;

    public Cell(CellStates state){
        this.state = state;
    }

    public CellStates getState(){
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    @Override
    public Cell clone() {
        return new Cell(this.state);
    }

    @Override
    public String toString(){
        return state.toString();
    }
}
