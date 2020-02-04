package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class FireParameter extends Parameter {
    private double probCatch;

    public FireParameter(){
        this("Simulation Team 7", 25, 25, .15);
    }

    public FireParameter(String fileAuthor, Integer length, Integer width, double prob){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        probCatch = prob;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.BURNING, States.EMPTY, States.ALIVE));
    }

    public double getThreshold() {
        return probCatch;
    }

    @Override
    public String toString(){
        return "Fire Simulation";
    }
}
