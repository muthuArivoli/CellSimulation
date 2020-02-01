package simulation;


public abstract class Simulation {
    protected Graph<Cell> myGrid;

    public Simulation(){
        createGraph();
    }

    public void createGraph(){

    }

    protected abstract Cell getNextState(Cell cell);

    public void update(){
        Graph<Cell> newGrid = new Graph<>();
        for(Cell c:myGrid.getVertices()){
            newGrid.addVertex(getNextState(c),myGrid.getNeighbors(c));
        }
    }

    public void returnGraph(){

    }
}
