package simulation;


import java.util.Iterator;
import java.util.List;

public abstract class Simulation {
    protected Graph<Cell> myGrid;

    public Simulation(){
        createGraph();
    }

    public void createGraph(){

    }

    protected abstract void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it);

    public void update(){
        Graph<Cell> newGrid = new Graph<>(myGrid);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c,myGrid.getNeighbors(c), newGrid, it);
        }
        myGrid = newGrid;
    }

    public void returnGraph(){

    }
}
