package simulation;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RectangleNeighborBehavior implements NeighborBehavior {

    @Override
    public Collection myNeighbors(int row, int col, int gridWidth, int gridLength) {
        List<int[]> neighbors = new ArrayList<>();
        if(row!=0){
            neighbors.add(new int[] {row-1,col});
        }
        if(row!=gridWidth-1){
            neighbors.add(new int[] {row+1,col});
        }
        if(col!=0){
            neighbors.add(new int[] {row,col-1});
        }
        if(col!=gridLength){
            neighbors.add(new int[] {row,col+1});
        }
        return neighbors;
    }
}
