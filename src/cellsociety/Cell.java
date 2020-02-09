package cellsociety;

import simulation.Clonable;

public class Cell implements Clonable {

    private CellStates state;

    public Cell(CellStates state){
        this.state = state;
    }

    public Cell(Cell c){
        state = c.getState();
    }

    public CellStates getState(){
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return state.toString();
    }

    @Override
    public Object clones() {
        return new Cell(state);
    }
}
