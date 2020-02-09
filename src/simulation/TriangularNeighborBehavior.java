package simulation;

import java.util.Collection;

public class TriangularNeighborBehavior extends TesselatingNeigborBehvaior {
    public TriangularNeighborBehavior(NeighborBehavior myNeighborBehavior){
        super(myNeighborBehavior);
    }
    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        Collection<int[]> c = super.getNeighbors(row,col,gridWidth,gridLength);
        if(col%2==1 && row!=0){
            if(col!=0){
                c.add(new int[] {row-1,col-1});
            }
            if(col!=row-1){
                c.add(new int[] {row-1,col+1});
            }
        }

    }
}
