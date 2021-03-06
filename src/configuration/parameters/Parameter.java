package configuration.parameters;
import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import javafx.scene.shape.Shape;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class Parameter {
    protected String author;
    protected Integer gridLength;
    protected Integer gridWidth;
    protected double percentage;
    protected ArrayList<CellState> possibleStates;
    protected Shape shape;
    protected String gridType;

    public Parameter(){}

    public abstract double getThreshold();

    public abstract Cell makeCell();

    public String getAuthor(){ return author; }
    public Integer getGridLength(){
        return gridLength;
    }
    public Integer getGridWidth(){
        return gridWidth;
    }
    public List<CellState> getPossibleStates(){
        return possibleStates;
    }
    public double getPercentage(){ return percentage; }

    @Override
    public abstract String toString();

    public String getGridType(){
        return gridType;
    }

    public abstract Simulation makeSimulation(List<List<Cell>> initialGrid, Parameter currentParam);
}
