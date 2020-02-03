package configuration.parameters;
import cellsociety.Cell;
import configuration.GridBuilder;
import configuration.States;

import java.io.File;
import java.util.ArrayList;

public abstract class Parameter {
    public Integer gridLength;
    public Integer gridWidth;
    public ArrayList<States> possibleStates;

    public Parameter(){}

    public void createGrid(File file){
        GridBuilder grid = new GridBuilder();
        ArrayList<ArrayList<Cell>> initialGrid = grid.makeGrid(file);
    }

    public abstract double getThreshold();

    public Integer getGridLength(){
        return gridLength;
    }
    public Integer getGridWidth(){
        return gridWidth;
    }
    public ArrayList<States> getPossibleStates(){
        return possibleStates;
    }

    @Override
    public abstract String toString();
}
