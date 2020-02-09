package simulation;


import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Simulation {

    protected Grid myGrid;
    protected int gridLength;
    protected int gridWidth;

    public Simulation(Collection grid){
        myGrid=new RectangularGrid(grid, gridLength, gridWidth);
    }



    //protected abstract void getNextState(Cell cell, Collection neighbor, Grid newGrid, Iterator<Cell> it);

    public abstract void update();
        /*Grid newGrid = new RectangularGrid(gridLength,gridWidth);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c,myGrid.getNeighbors(c), newGrid, it);
        }
        myGrid = newGrid;
        newGrid.createGraph(returnGraph());*/

    public Collection returnGraph(){
        return myGrid.returnGraph();
    }
}
