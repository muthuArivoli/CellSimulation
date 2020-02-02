package simulation;

import java.util.Iterator;
import java.util.List;

public class GameOfLifeSimulation extends Simulation {
    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        int liveNeighbors = 0;
        for(Cell c: neighbor){
            if(c.getState() == 1){
                liveNeighbors++;
            }
        }
        if(liveNeighbors>=3 || (liveNeighbors==2 && cell.getState() == 1)){
            it.next().setState(1);
        }
        else{
            it.next().setState(0);
        }
    }
}
