package simulation;


import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Simulation {

    protected Graph<Cell> myGrid;

    public Simulation(Collection grid){
        createGraph(grid);
    }

    private void createGraph(Collection array){
        myGrid = new Graph<Cell>();
        System.out.println(array.size());
        List<List<Cell>> grid = new ArrayList<List<Cell>>(array);
        System.out.println(grid.get(0).size());
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

    protected abstract void getNextState(Cell cell, List<Cell> neighbor, Graph<Cell> newGrid, Iterator<Cell> it);

    public void update(){
        Graph<Cell> newGrid = new Graph<>(myGrid);
        Iterator it = newGrid.getVertices().iterator();
        for(Cell c:myGrid.getVertices()){
            getNextState(c,myGrid.getNeighbors(c), newGrid, it);
        }
        myGrid = newGrid;
    }

    public Graph<Cell> returnGraph(){
        return myGrid;
    }
}
