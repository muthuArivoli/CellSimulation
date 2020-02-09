package simulation;

import java.util.Collection;

public class HexagonalGrid extends Grid {
    public HexagonalGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth);
        myNeighbors = new HexagonalNeighborBehavior(new RectangleNeighborBehavior());
    }
}
