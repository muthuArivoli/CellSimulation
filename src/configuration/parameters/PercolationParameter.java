package configuration.parameters;

import cellsociety.cellstate.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PercolationParameter extends Parameter {
    private double threshold;


    public PercolationParameter(){
        this("Simulation Team 7", 25, 25, .7);
    }

    public PercolationParameter(String type, Integer length, Integer width, double perc){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.OPEN, State.BLOCKED, State.FULL));
    }

    public PercolationCell makeCell(double prob){
        CellState state;
        if(Math.random() < prob){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(0);
        }
        return new PercolationCell(state);
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
