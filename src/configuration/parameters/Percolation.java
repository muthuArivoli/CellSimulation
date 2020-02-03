package configuration.parameters;

import configuration.States;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Percolation extends Parameter {
    private double threshold;

    public Percolation(){
        this(25, 25, 0);
    }

    public Percolation(Integer length, Integer width, double thresh){
        gridLength = length;
        gridWidth = width;
        threshold = thresh;
        possibleStates = new ArrayList<States>(Arrays.asList(States.BURNING, States.EMPTY, States.TREE));
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
