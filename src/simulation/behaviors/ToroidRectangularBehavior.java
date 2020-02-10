package simulation.behaviors;

import java.util.Collection;

public class ToroidRectangularBehavior extends TesselatingNeigborBehvaior {

    public ToroidRectangularBehavior(NeighborBehavior myNeighborBehvaior) {
        super(myNeighborBehvaior);
    }

    @Override
    public Collection<int[]> getNeighbors(int row, int col, int gridWidth, int gridLength) {
        Collection<int[]> c = super.getNeighbors(row,col,gridWidth,gridLength);
        if(row==0){
           c.add(new int[] {gridLength,col});
        }
        if(row!=gridLength-1){
            c.add(new int[] {0,col});
        }
        if(col!=0){
            c.add(new int[] {row,gridWidth});
        }
        if(col!=gridLength - 1){
            c.add(new int[] {row,0});
        }
        return c;
    }
}
