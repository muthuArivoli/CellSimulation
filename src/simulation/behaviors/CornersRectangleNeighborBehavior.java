package simulation.behaviors;

import java.util.Collection;

public class CornersRectangleNeighborBehavior extends TesselatingNeigborBehvaior {

    public CornersRectangleNeighborBehavior(NeighborBehavior myNeighborBehvaior) {
        super(myNeighborBehvaior);
    }
    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        Collection<int[]> c = super.getNeighbors(row,col,gridWidth,gridLength);
        if(row!=0 && col!=0){
            c.add(new int[] {row-1,col-1});
        }
        if(row!=gridWidth-1 && col!=0){
            c.add(new int[] {row+1,col-1});
        }
        if(row!=0 && col!=gridLength - 1){
            c.add(new int[] {row-1,col+1});
        }
        if(row!= gridWidth - 1 && col!=gridLength - 1){
            c.add(new int[] {row+1,col+1});
        }
        return c;
    }
}
