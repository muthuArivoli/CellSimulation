package simulation;

import java.util.Collection;

public abstract class TesselatingNeigborBehvaior implements NeighborBehavior {

    private NeighborBehavior myNeighborBehavior;

    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        return myNeighborBehavior.getNeighbors(row,col,gridWidth,gridLength);
    }
}
