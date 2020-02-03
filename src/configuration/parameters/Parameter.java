package configuration.parameters;
import cellsociety.Cell;
import cellsociety.CellStates;
import configuration.GridBuilder;
import configuration.States;

import java.io.File;
import java.util.ArrayList;

public abstract class Parameter {
    public String author;
    public Integer gridLength;
    public Integer gridWidth;
    public ArrayList<CellStates> possibleStates;

    public Parameter(){}

    public abstract double getThreshold();

    public String getAuthor(){ return author; }
    public Integer getGridLength(){
        return gridLength;
    }
    public Integer getGridWidth(){
        return gridWidth;
    }
    public ArrayList<CellStates> getPossibleStates(){
        return possibleStates;
    }

    @Override
    public abstract String toString();
}
