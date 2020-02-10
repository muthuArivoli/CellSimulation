package simulation.behaviors;

import simulation.behaviors.NeighborBehavior;

import java.util.Collection;

public abstract class TesselatingNeigborBehvaior implements NeighborBehavior {

    private NeighborBehavior myNeighborBehavior;

    public TesselatingNeigborBehvaior(NeighborBehavior myNeighborBehvaior){
        this.myNeighborBehavior = myNeighborBehvaior;
    }

    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        return myNeighborBehavior.getNeighbors(row,col,gridWidth,gridLength);
    }
}
