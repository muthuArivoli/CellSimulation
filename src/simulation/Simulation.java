package simulation;


public abstract class Simulation {
    protected Graph<Cell> myGrid;

    public Simulation(){
        createGraph();
    }

    public void createGraph(){

    }

    protected abstract void getNextState();

    public void update(){

    }

    public void returnGraph(){

    }
}
