package simulation.grid;

import simulation.behaviors.RectangleNeighborBehavior;
import simulation.behaviors.TriangularNeighborBehavior;
import simulation.grid.Grid;

import java.util.Collection;

public class TriangleGrid extends Grid {
    public TriangleGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth, new TriangularNeighborBehavior(new RectangleNeighborBehavior()));
    }
}
