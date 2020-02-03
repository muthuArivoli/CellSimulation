package configuration.parameters;

import cellsociety.Cell;
import configuration.GridBuilder;
import configuration.States;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Fire extends Parameter {
    private double probCatch;

    public Fire(){
        this(25, 25, .25);
    }

    public Fire(Integer length, Integer width, double prob){
        gridLength = length;
        gridWidth = width;
        probCatch = prob;
        possibleStates = new ArrayList<States>(Arrays.asList(States.BURNING, States.EMPTY, States.TREE));
    }

    public double getThreshold() {
        return probCatch;
    }

    @Override
    public String toString(){
        return "Fire Simulation";
    }
}
