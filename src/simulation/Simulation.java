package simulation;


import java.util.Iterator;

public abstract class Simulation {
    protected Graph<Cell> myGrid;

    public Simulation(){
        createGraph();
    }

    public void createGraph(){

    }

    protected abstract void getNextState(Cell cell);

    public void update(){
        Graph<Cell> newGrid = new Graph<>(myGrid);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c);
        }
        myGrid = newGrid;
    }

    public void returnGraph(){

    }
}
