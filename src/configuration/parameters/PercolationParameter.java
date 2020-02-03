package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class PercolationParameter extends Parameter {
    private double threshold;

    public PercolationParameter(){
        this("Simulation Team 7", 25, 25, 0);
    }

    public PercolationParameter(String fileAuthor, Integer length, Integer width, double thresh){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        threshold = thresh;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.OPEN, States.BLOCKED, States.FULL));
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
