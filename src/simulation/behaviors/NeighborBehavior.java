package simulation.behaviors;

import java.util.Collection;

public interface NeighborBehavior {
    Collection<int[]> getNeighbors(int row,int col, int gridWidth, int gridLength);
}
