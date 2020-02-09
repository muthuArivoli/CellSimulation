package simulation;

import cellsociety.Cell;
import configuration.States;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GameOfLifeSimulation extends CurrStateSimulation {
    public GameOfLifeSimulation(Collection grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, Collection<Cell> neighbor,  Grid newGrid, Iterator<Cell> it){
        int liveNeighbors = 0;
        for(Cell c: neighbor){
            if(c.getState() == States.ALIVE){
                liveNeighbors++;
            }
        }
        if((liveNeighbors>=3 && cell.getState() == States.DEAD) || (liveNeighbors==2 && cell.getState() == States.ALIVE)){
            it.next().setState(States.ALIVE);
        }
        else{
            it.next().setState(States.DEAD);
        }
    }
}
