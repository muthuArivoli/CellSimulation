package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class SegregationParameter extends Parameter {
    private double probEaten;

    public SegregationParameter(){
        this("Simulation Team 7", 25, 25, .25);
    }


    public SegregationParameter(String fileAuthor, Integer length, Integer width, Double prob){
        author = fileAuthor;
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
