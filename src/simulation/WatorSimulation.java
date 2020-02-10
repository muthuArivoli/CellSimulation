package simulation;

import cellsociety.Cell;

import cellsociety.cellstate.CellState;
import cellsociety.cellstate.State;
import configuration.parameters.Parameter;
import simulation.grid.Grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WatorSimulation extends CurrStateSimulation {

    public WatorSimulation(Collection grid, Parameter param) {
        super(grid, param);
    }

    protected void getNextState(Cell cell, Collection<Cell> neighbors, Grid newGrid, Iterator<Cell> it){
        Cell myTemp = it.next();
        Collection<Cell> neighbor = newGrid.getNeighbors(myTemp);
        CellState nextState = myTemp.getNextState();
        if(nextState == State.DEAD || nextState == State.EMPTY){
            myTemp.setState(State.EMPTY);
            return;
        }
        CellState currState = myTemp.getState();
        if(nextState == State.MOVE){
            myTemp.setState(State.EMPTY);
        }
        if(currState==State.PREY){
            movetoEmptyCell(neighbor, currState);
        }
        else if (currState == State.PREDATOR){
            for(Cell c:neighbor){
                if(c.getState()== State.PREY){
                    c.setState(currState);
                    return;
                }
            }
            movetoEmptyCell(neighbor,currState);
        }
    }
    private void movetoEmptyCell(Collection<Cell> neighbor, CellState currState){
        List<Cell> available = new ArrayList<>();
        for(Cell c:neighbor){
            if(c.getState() == State.EMPTY){
                available.add(c);
            }
        }
        if(available.size()>0) {
            int randValue = (int)( Math.random() * available.size());
            available.get(randValue).setState(currState);
        }
    }
}
