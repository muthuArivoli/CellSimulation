package simulation;

import java.util.Iterator;
import java.util.List;

public class PercolationSimulation extends Simulation{
    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        if(cell.getState() == 1) {
            boolean percolatedCell = false;
            for (Cell c : neighbor) {
                if (c.getState() == 2) {
                    percolatedCell = true;
                }
            }
            if (percolatedCell) {
                it.next().setState(2);
                return;
            }
            it.next();
        }
    }
}
