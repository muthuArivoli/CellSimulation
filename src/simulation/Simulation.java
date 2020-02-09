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

    public Simulation(Collection<Cell> grid, int gridLength, int gridWidth){
        this.gridWidth = gridWidth;
        this.gridLength = gridLength;
        this.gridWidth = grid.size();
        myGrid = new RectangularGrid(grid, gridLength, gridWidth);
    }

    public abstract void update();

    public Collection returnGraph(){
        return myGrid.returnGraph();
    }
}
