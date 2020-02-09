package simulation;

import cellsociety.Cell;

import java.util.Collection;

public class RectangularGrid extends Grid {
    public RectangularGrid(Collection<Cell> grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth,new RectangleNeighborBehavior());
    }
}
