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
    private NeighborBehavior myNeighbors;
    public Grid(Collection grid, int gridLength, int gridWidth, NeighborBehavior myNeighbors){
        this.myNeighbors = myNeighbors;
        this.gridLength = gridLength;
        this.gridWidth = gridWidth;
        createGraph(grid);
    }

    public void createGraph(Collection array){
        List<List<Cell>> grid = new ArrayList<List<Cell>>(array);
        for(int i=0; i<grid.size();i++){
            for(int k=0; k<grid.get(i).size();k++){
                Collection c = myNeighbors.getNeighbors(i,k,gridWidth,gridLength);
                Iterator<int[]> it = c.iterator();
                while(it.hasNext()){
                    int[] neighbor = it.next();
                    myGrid.addEdge(grid.get(neighbor[0]).get(neighbor[1]),grid.get(i).get(k));
                }
            }
        }
    }

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

    public Collection<Cell> getVertices(){
        return myGrid.getVertices();
    }
    public Collection getNeighbors(Cell c){
        return myGrid.getNeighbors(c);
    }
}
