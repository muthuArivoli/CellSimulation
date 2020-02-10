package simulation;

import cellsociety.Cell;
import cellsociety.cellstate.State;
import configuration.parameters.Parameter;
import simulation.grid.Grid;

import java.util.Collection;
import java.util.Iterator;

public class PercolationSimulation extends CurrStateSimulation{

    public PercolationSimulation(Collection grid, Parameter param) {
        super(grid, param);
        gridType = param.getGridType();
    }

    protected void getNextState(Cell cell, Collection<Cell> neighbor, Grid newGrid, Iterator<Cell> it){
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
        }
        it.next();
    }
}
