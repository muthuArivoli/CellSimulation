package simulation;

import cellsociety.Cell;
import configuration.States;

import java.util.Iterator;
import java.util.List;

public class PercolationSimulation extends Simulation{
    public PercolationSimulation(List<List<Cell>> grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        if(cell.getState() == States.OPEN) {
            boolean percolatedCell = false;
            for (Cell c : neighbor) {
                if (c.getState() == States.FULL) {
                    percolatedCell = true;
                }
            }
            if (percolatedCell) {
                it.next().setState(States.FULL);
                return;
            }
            it.next();
        }
    }
}
