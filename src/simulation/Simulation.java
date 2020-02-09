package simulation;


import cellsociety.Cell;
import configuration.parameters.Parameter;
import simulation.grid.Grid;
import simulation.grid.RectangularGrid;

import java.util.Collection;

public abstract class Simulation {

    protected Grid myGrid;
    protected int gridLength;
    protected int gridWidth;
    protected String gridType;

    public Simulation(Collection<Cell> grid, Parameter param){
        this.gridWidth = param.getGridWidth();
        this.gridLength = param.getGridLength();
        myGrid = new RectangularGrid(grid, gridLength, gridWidth);
    }

    public abstract void update();

    public Collection returnGraph(){
        return myGrid.returnGraph();
    }

    public String getGridType(){
        return gridType;
    }
}
