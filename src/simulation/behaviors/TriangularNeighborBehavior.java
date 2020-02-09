package simulation.behaviors;

import java.util.Collection;
@Deprecated
public class TriangularNeighborBehavior extends TesselatingNeigborBehvaior {
    public TriangularNeighborBehavior(NeighborBehavior myNeighborBehavior){
        super(myNeighborBehavior);
    }
    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        Collection<int[]> c = super.getNeighbors(row,col,gridWidth,gridLength);
        if((row+col)%2==1){
            if(col!=0){
                c.add(new int[] {row-1,col-1});
            }
            if(col!=row-1){
                c.add(new int[] {row-1,col+1});
            }
        }
        return c;
    }
}
