package simulation;

import cellsociety.Cell;
import cellsociety.cellstate.State;
import cellsociety.cellstate.WatorCell;
import configuration.parameters.Parameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WatorSimulation extends Simulation {

    public WatorSimulation(Collection grid, Parameter param) {
        super(grid);
        gridType = param.getGridType();
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        Cell myTemp = it.next();
        if(((WatorCell)myTemp.getState()).getState() == State.EMPTY){
            return;
        }
        ((WatorCell)cell.getState()).decrementEnergy();
        ((WatorCell)cell.getState()).decrementBirth();
        if(((WatorCell)cell.getState()).getState()== State.PREY){
            Cell currCell = myTemp;
            List<Integer> available = new ArrayList<>();
            for(int i=0;i<newGrid.getAdjList().get(currCell).size();i++){
                if(neighbor.get(i).getState() == State.EMPTY){
                    available.add(Integer.valueOf(i));
                }
            }
            int randValue =(int) Math.random()*neighbor.size();
            neighbor.get(randValue).setState(cell.getState());
            if(((WatorCell)cell.getState()).getBirth()!=0){
                cell = new WatorCell(State.EMPTY);
            }

        }
        else if (((WatorCell)cell.getState()).getState() == State.PREDATOR){
            if(((WatorCell)cell.getState()).getEnergy()<=0){
                cell = new WatorCell(State.EMPTY);
                return;
            }
            boolean move = false;
            for(int i=0;i<neighbor.size();i++){
                if(neighbor.get(i).getState()== State.PREY){
                    neighbor.get(i).setState(cell.getState());
                    move = true;
                    break;
                }
            }
            if(!move){
                for(int i=0;i<neighbor.size();i++){
                    if(neighbor.get(i).getState()== State.EMPTY){
                        neighbor.get(i).setState(cell.getState());
                        break;
                    }
                }
            }
            if(((WatorCell)cell.getState()).getBirth()!=0){
                cell = new WatorCell(State.EMPTY);
            }
        }
    }
}
