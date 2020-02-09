package simulation;

import java.util.Collection;

public interface NeighborBehavior {
    Collection myNeighbors(int row,int col, int gridWidth, int gridLength);
}
