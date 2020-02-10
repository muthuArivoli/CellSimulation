package simulation;

import cellsociety.Cell;
import cellsociety.cellstate.State;
import configuration.parameters.Parameter;
import simulation.grid.Grid;

import java.util.Collection;
import java.util.Iterator;

public class GameOfLifeSimulation extends CurrStateSimulation {
    public GameOfLifeSimulation(Collection grid, Parameter param) {
        super(grid, param);
    }
    protected void getNextState(Cell cell, Collection<Cell> neighbor, Grid newGrid, Iterator<Cell> it){
        int liveNeighbors = 0;
        for(Cell c: neighbor){
            if(c.getState() == State.ALIVE){
                liveNeighbors++;
            }
        }
        if((liveNeighbors>=3 && cell.getState() == State.DEAD) || (liveNeighbors==2 && cell.getState() == State.ALIVE)){
            it.next().setState(State.ALIVE);
        }
        else{
            it.next().setState(State.DEAD);
        }
    }
}
