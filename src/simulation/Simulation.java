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
        createGraph(grid);
    }



    protected abstract void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it);

    public void update(){
        Graph<Cell> newGrid = new Graph<>(myGrid);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c,myGrid.getNeighbors(c), newGrid, it);
        }
        myGrid = newGrid;
        createGraph(returnGraph());
    }

    public Collection returnGraph(){
        myGrid.returnGraph();
    }
}
