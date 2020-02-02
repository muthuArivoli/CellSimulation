package cellsociety;

import configuration.States;
import simulation.Clonable;

public class Cell implements Clonable {

    private States state;

    public Cell(States state){
        this.state = state;
    }

    public States getState(){
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    @Override
    public Cell clone() {
        return new Cell(this.state);
    }
}
