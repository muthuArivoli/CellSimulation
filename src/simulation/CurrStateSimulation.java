package simulation;

import cellsociety.Cell;

import java.util.Collection;
import java.util.Iterator;

public abstract class CurrStateSimulation extends Simulation {
    public CurrStateSimulation(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength, gridWidth);
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

    protected abstract void getNextState(Cell c, Collection<Cell> neighbors, Grid newGrid, Iterator<Cell> it);
}
