package configuration.parameters;

import cellsociety.cellstate.CellState;
import cellsociety.cellstate.FireCell;
import cellsociety.cellstate.SegregationCell;
import cellsociety.cellstate.State;

import java.util.ArrayList;
import java.util.Arrays;

public class SegregationParameter extends Parameter {
    private double probEaten;

    public SegregationParameter(){
        this("Simulation Team 7", 25, 25, .5, .7);
    }


    public SegregationParameter(String type, Integer length, Integer width, Double prob, Double perc){
        gridType = type;
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.MINORITY, State.EMPTY, State.MAJORITY));
    }

    public SegregationCell makeCell(double prob){
        CellState state;
        double roll = Math.random();
        if(roll > prob + (1 - prob)/2){
            state = possibleStates.get(2);
        }
        else if(roll > prob){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(0);
        }
        return new SegregationCell(state);
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Segregation Simulation";
    }
}
