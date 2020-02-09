package simulation;

import java.util.Collection;

public class RectangularGrid extends Grid {
    public RectangularGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth,new RectangleNeighborBehavior());
    }
}
