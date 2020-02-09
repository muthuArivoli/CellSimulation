package simulation;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RectangularGrid extends Grid {
    public RectangularGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth);
        myNeighbors = new RectangleNeighborBehavior();
    }
}
