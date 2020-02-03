package simulation;

import cellsociety.Cell;
import configuration.States;

import java.util.Iterator;
import java.util.List;

public class GameOfLifeSimulation extends Simulation {
    public GameOfLifeSimulation(List<List<Cell>> grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        int liveNeighbors = 0;
        for(Cell c: neighbor){
            if(c.getState() == States.LIVE){
                liveNeighbors++;
            }
        }
        if(liveNeighbors>=3 || (liveNeighbors==2 && cell.getState() == States.LIVE)){
            it.next().setState(States.LIVE);
        }
        else{
            it.next().setState(States.DEAD);
        }
    }
}
