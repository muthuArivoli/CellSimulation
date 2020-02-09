package simulation;

import cellsociety.Cell;
import cellsociety.cellstate.State;
import configuration.parameters.Parameter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SegregationSimulation extends CurrStateSimulation {
    private double threshold;

    public SegregationSimulation(Collection grid, Parameter param) {
        super(grid);
        threshold = param.getThreshold();
        gridType = param.getGridType();
    }

    protected void getNextState(Cell cell, Collection<Cell> neighbor, Grid newGrid, Iterator<Cell> it){
        if(cell.getState() == States.EMPTY){
            it.next();
            return;
        }
        int similarNeighbors = 0;
        for(Cell c:neighbor){
            if(c.getState() == cell.getState()){
                similarNeighbors++;
            }
        }
        // Check that state 0 should be EMPTY
        if((double)similarNeighbors/neighbor.size() <= threshold){
            Iterator<Cell> initialIt = newGrid.getVertices().iterator();
            for(Cell c:myGrid.getVertices()){
                Cell thisCell = initialIt.next();
                if(c.getState() == State.EMPTY && thisCell.getState() == State.EMPTY){
                    thisCell.setState(cell.getState());
                    it.next().setState(State.EMPTY);
                    return;
                }
            }
        }
        it.next();
    }
}
