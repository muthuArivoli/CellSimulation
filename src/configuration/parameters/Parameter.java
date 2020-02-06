package configuration.parameters;
import cellsociety.Cell;
import cellsociety.CellStates;
import configuration.GridBuilder;
import configuration.States;

import java.io.File;
import java.util.ArrayList;

public abstract class Parameter {
    protected String author;
    protected Integer gridLength;
    protected Integer gridWidth;
    protected double percentage;
    protected ArrayList<CellStates> possibleStates;

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
    public double getPercentage(){ return percentage; }

    @Override
    public abstract String toString();
}
