package simulation;

import cellsociety.Cell;
import configuration.States;
import configuration.parameters.Parameter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FireSimulation extends  Simulation {

    private double probability;

    public FireSimulation(Collection grid, Parameter param){
        super(grid);
        probability = param.getThreshold();
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
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
        if(neighborOnFire && Math.random()>probability){
            it.next().setState(States.BURNING);
            return;
        }
        it.next();
    }
}
