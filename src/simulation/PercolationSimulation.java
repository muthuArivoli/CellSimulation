package simulation;

import cellsociety.Cell;
import configuration.State;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PercolationSimulation extends Simulation{
    public PercolationSimulation(Collection grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        if(cell.getState() == State.OPEN) {
            boolean percolatedCell = false;
            for (Cell c : neighbor) {
                if (c.getState() == State.FULL) {
                    percolatedCell = true;
                }
            }
            if(neighbor.size() < 4){
                percolatedCell = true;
            }
            if (percolatedCell) {
                it.next().setState(State.FULL);
                return;
            }
            it.next();
        }
    }
}
