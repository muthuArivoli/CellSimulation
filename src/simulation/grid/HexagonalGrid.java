package simulation.grid;

import simulation.behaviors.HexagonalNeighborBehavior;
import simulation.behaviors.RectangleNeighborBehavior;

import java.util.Collection;

public class HexagonalGrid extends Grid {
    public HexagonalGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth, new HexagonalNeighborBehavior(new RectangleNeighborBehavior()));
    }
}
