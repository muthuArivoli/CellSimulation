package simulation;

import cellsociety.Cell;
import cellsociety.cellstate.State;
import configuration.parameters.Parameter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FireSimulation extends Simulation {

    private double probability;

    public FireSimulation(Collection grid, Parameter param){
        super(grid);
        probability = param.getThreshold();
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){

        if(cell.getState() == State.BURNING) {
            it.next().setState(State.EMPTY);
            return;
        }
        boolean neighborOnFire = false;
        for(Cell c:neighbor){
            if(c.getState() == State.BURNING){
                neighborOnFire = true;
            }
        }
        if(cell.getState() == State.ALIVE && neighborOnFire && Math.random()>probability){
            it.next().setState(State.BURNING);
            return;
        }
        it.next();
    }
}
