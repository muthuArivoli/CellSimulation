package simulation;

import cellsociety.Cell;
import cellsociety.WatorCell;
import configuration.States;
import configuration.WatorState;
import configuration.parameters.Parameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WatorSimulation extends Simulation {

    public WatorSimulation(Collection<Cell> grid, Parameter param, int gridLength, int gridWidth) {
        super(grid, gridLength, gridWidth);
    }

    protected void getNextState(Cell cell, Collection neighbors, Grid newGrid, Iterator<Cell> it){
        List<Cell> neighbor= new ArrayList<>(neighbors);
        Cell myTemp = it.next();
        if(((WatorState)myTemp.getState()).getState() == States.EMPTY){
            return;
        }
        cell.decrementEnergy();
        cell.decrementBirth();
        if(cell.getState()== States.PREY){
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

    @Override
    public void update() {
        Grid newGrid = new RectangularGrid(returnGraph(),gridLength,gridWidth);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c,myGrid.getNeighbors(c), newGrid, it);
        }
        myGrid = newGrid;
        newGrid.createGraph(returnGraph());
    }
}
