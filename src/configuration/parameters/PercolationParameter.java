package configuration.parameters;

import cellsociety.CellStates;
import configuration.State;

import java.util.ArrayList;
import java.util.Arrays;

public class PercolationParameter extends Parameter {
    private double threshold;


    public PercolationParameter(){
        this("Simulation Team 7", 25, 25, .7);
    }

    public PercolationParameter(String fileAuthor, Integer length, Integer width, double perc){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(State.OPEN, State.BLOCKED, State.FULL));
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
