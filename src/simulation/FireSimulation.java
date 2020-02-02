package simulation;

import cellsociety.Cell;

import java.util.Iterator;
import java.util.List;

public class FireSimulation extends  Simulation {
    private double probability = 0.5;
    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        if(cell.getState() == 2) {
            it.next().setState(0);
            return;
        }
        boolean neighborOnFire = false;
        for(Cell c:neighbor){
            if(c.getState() == 2){
                neighborOnFire = true;
            }
        }
        if(neighborOnFire && Math.random()>probability){
            it.next().setState(2);
            return;
        }
        it.next();
    }
}
