package simulation;

import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Grid {
    protected Graph<Cell> myGrid;
    protected int gridLength;
    protected int gridWidth;

    public Grid(Collection grid, int gridLength, int gridWidth){
        this.gridLength = gridLength;
        this.gridWidth = gridWidth;
        createGraph(grid);
    }


    protected abstract void createGraph(Collection array);

    public Collection returnGraph(){
        Iterator<Cell> it = myGrid.getVertices().iterator();
        List<List<Cell>> cartesianGrid = new ArrayList<>();
        for(int i=0;i<gridLength;i++){
            List<Cell> temp = new ArrayList<>();
            cartesianGrid.add(temp);
            for(int k=0;k<gridWidth;k++){
                temp.add(it.next());
            }
        }
        return cartesianGrid;
    }
}
