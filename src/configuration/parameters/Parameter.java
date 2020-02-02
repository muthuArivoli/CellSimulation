package configuration.parameters;
import cellsociety.Cell;
import configuration.GridBuilder;
import configuration.States;

import java.io.File;
import java.util.ArrayList;

public abstract class Parameter {
    public Parameter(){}

    public void createGrid(File file){
        GridBuilder grid = new GridBuilder();
        ArrayList<ArrayList<Cell>> initialGrid = grid.readFile(file);
    }

    @Override
    public abstract String toString();
}
