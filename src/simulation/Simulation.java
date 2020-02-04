package simulation;


import cellsociety.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Simulation {

    protected Graph<Cell> myGrid;
    private int gridLength;
    private int gridWidth;

    public Simulation(Collection grid){
        createGraph(grid);
    }

    private void createGraph(Collection array){
        List<List<Cell>> grid = new ArrayList<List<Cell>>(array);
        gridLength = grid.size();
        gridWidth = grid.get(0).size();
        myGrid = new Graph<Cell>();
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
        createGraph(returnGraph());
    }

    public List<List<Cell>> returnGraph(){
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
