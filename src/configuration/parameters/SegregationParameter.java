package configuration.parameters;

import cellsociety.CellStates;
import configuration.State;

import java.util.ArrayList;
import java.util.Arrays;

public class SegregationParameter extends Parameter {
    private double probEaten;

    public SegregationParameter(){
        this("Simulation Team 7", 25, 25, .5, .7);
    }


    public SegregationParameter(String fileAuthor, Integer length, Integer width, Double prob, Double perc){
        author = fileAuthor;
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(State.MINORITY, State.MAJORITY, State.EMPTY));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Segregation Simulation";
    }
}
