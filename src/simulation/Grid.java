package simulation;

import cellsociety.Cell;

import java.util.Collection;

public abstract class Grid {
    private Graph<Cell> myGrid;
    public Grid(Collection grid){
        createGraph();
    }

    protected abstract void createGraph();
}
