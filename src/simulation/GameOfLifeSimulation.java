package simulation;

import cellsociety.Cell;
import configuration.State;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GameOfLifeSimulation extends Simulation {
    public GameOfLifeSimulation(Collection grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
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
