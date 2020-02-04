package simulation;

import cellsociety.Cell;
import configuration.States;
import configuration.WatorState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WatorSimulation extends Simulation {

    public WatorSimulation(Collection grid) {
        super(grid);
    }

    protected void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it){
        Cell myTemp = it.next();
        if(((WatorState)myTemp.getState()).getState() == States.EMPTY){
            return;
        }
        ((WatorState)cell.getState()).decrementEnergy();
        ((WatorState)cell.getState()).decrementBirth();
        if(((WatorState)cell.getState()).getState()== States.PREY){
            Cell currCell = myTemp;
            List<Integer> available = new ArrayList<>();
            for(int i=0;i<newGrid.getAdjList().get(currCell).size();i++){
                if(neighbor.get(i).getState() == States.EMPTY){
                    available.add(Integer.valueOf(i));
                }
            }
            int randValue =(int) Math.random()*neighbor.size();
            neighbor.get(randValue).setState(cell.getState());
            if(((WatorState)cell.getState()).getBirth()!=0){
                cell.setState(new WatorState(States.EMPTY));
            }

        }
        else if (((WatorState)cell.getState()).getState() == States.PREDATOR){
            if(((WatorState)cell.getState()).getEnergy()<=0){
                cell.setState(new WatorState(States.EMPTY));
                return;
            }
            boolean move = false;
            for(int i=0;i<neighbor.size();i++){
                if(neighbor.get(i).getState()==States.PREY){
                    neighbor.get(i).setState(cell.getState());
                    move = true;
                    break;
                }
            }
            if(!move){
                for(int i=0;i<neighbor.size();i++){
                    if(neighbor.get(i).getState()==States.EMPTY){
                        neighbor.get(i).setState(cell.getState());
                        break;
                    }
                }
            }
            if(((WatorState)cell.getState()).getBirth()!=0){
                cell.setState(new WatorState(States.EMPTY));
            }
        }
    }
}
