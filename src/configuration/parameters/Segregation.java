package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class Segregation extends Parameter {
    private double probEaten;

    public Segregation(){
        this(25, 25, .25);
    }


    public Segregation(Integer length, Integer width, Double prob){
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.MAJORITY, States.MINORITY, States.EMPTY));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Segregation Simulation";
    }
}
