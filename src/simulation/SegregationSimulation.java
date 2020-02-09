package simulation;

import cellsociety.Cell;
import configuration.States;
import configuration.parameters.Parameter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SegregationSimulation extends CurrStateSimulation {
    private double threshold;

    public SegregationSimulation(Collection grid, Parameter param, int gridLength, int gridWidth) {
        super(grid, gridLength, gridWidth);
        threshold = param.getThreshold();
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
                if(c.getState() == States.EMPTY && thisCell.getState() == States.EMPTY){
                    thisCell.setState(cell.getState());
                    it.next().setState(States.EMPTY);
                    return;
                }
            }
        }
        it.next();
    }
}
