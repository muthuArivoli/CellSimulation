package simulation;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RectangularGrid extends Grid {

    public RectangularGrid(Collection grid, int gridLength, int gridWidth){
        super(grid,gridLength,gridWidth);
    }

    public void createGraph(Collection array){
        List<List<Cell>> grid = new ArrayList<List<Cell>>(array);
        for(int i=0; i<grid.size();i++){
            for(int k=0; k<grid.get(i).size();k++){
                List<Cell> neighbors = new ArrayList<>();
                if(i!=0){
                    neighbors.add(grid.get(i-1).get(k));
                }
                if(i!=grid.size()-1){
                    neighbors.add(grid.get(i+1).get(k));
                }
                if(k!=0){
                    neighbors.add(grid.get(i).get(k-1));
                }
                if(k!=grid.get(i).size()-1){
                    neighbors.add(grid.get(i).get(k+1));
                }
                myGrid.addVertex(grid.get(i).get(k),neighbors);
            }
        }
    }
}
