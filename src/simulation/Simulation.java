package simulation;


import Visualization.board.TriangularBoard;
import cellsociety.Cell;
import configuration.parameters.Parameter;
import simulation.grid.Grid;
import simulation.grid.HexagonalGrid;
import simulation.grid.RectangularGrid;
import simulation.grid.TriangleGrid;

import java.util.Collection;

public abstract class Simulation {

    private static final String TRIANGLE = "Triangle";
    private static final String HEXAGONAL = "Hexagon";
    protected Grid myGrid;
    protected int gridLength;
    protected int gridWidth;
    protected String gridType;

    public Simulation(Collection<Cell> grid, Parameter param){
        this.gridWidth = param.getGridWidth();
        this.gridLength = param.getGridLength();
        this.gridType = param.getGridType();
        createGrid(grid);
    }

    public abstract void update();

    public Collection returnGraph(){
        return myGrid.returnGraph();
    }

    public String getGridType(){
        return gridType;
    }

    private void createGrid(Collection<Cell> grid){
        if(this.gridType.equals(TRIANGLE)){
            myGrid = new TriangleGrid(grid, gridLength, gridWidth);
        }
        else if(this.gridType.equals(HEXAGONAL)){
            myGrid = new HexagonalGrid(grid, gridLength, gridWidth);
        }
        else{
            myGrid = new RectangularGrid(grid, gridLength, gridWidth);
        }
    }
}
