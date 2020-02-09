package simulation;

import cellsociety.Cell;
import configuration.States;
import configuration.parameters.Parameter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FireSimulation extends CurrStateSimulation {

    private double probability;

    public FireSimulation(Collection grid, Parameter param, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth);
        probability = param.getThreshold();
    }

    protected void getNextState(Cell cell, Collection<Cell> neighbor, Grid newGrid, Iterator<Cell> it){
        if(cell.getState() == States.BURNING) {
            it.next().setState(States.EMPTY);
            return;
        }
        boolean neighborOnFire = false;
        for(Cell c:neighbor){
            if(c.getState() == States.BURNING){
                neighborOnFire = true;
            }
        }
        if(cell.getState() == States.ALIVE && neighborOnFire && Math.random()>probability){
            it.next().setState(States.BURNING);
            return;
        }
        it.next();
    }
}
