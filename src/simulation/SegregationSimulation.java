package simulation;

import cellsociety.Cell;

import java.util.Iterator;
import java.util.List;

public class SegregationSimulation extends Simulation {
    private double threshold = 0.3;
    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        int similarNeighbors = 0;
        for(Cell c:neighbor){
            if(c.getState() == cell.getState()){
                similarNeighbors++;
            }
        }
        if((double)similarNeighbors/neighbor.size() <=threshold){
            Iterator<Cell> initialIt = newGrid.getVertices().iterator();
            for(Cell c:myGrid.getVertices()){
                Cell thisCell = initialIt.next();
                if(c.getState() == 0 && thisCell.getState() == 0){
                    thisCell.setState(cell.getState());
                    it.next().setState(0);
                    return;
                }
            }
        }
        it.next();
    }
}
