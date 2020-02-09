package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import cellsociety.cellstate.FireCell;
import cellsociety.cellstate.State;

import java.util.ArrayList;
import java.util.Arrays;

public class FireParameter extends Parameter {
    private double probCatch;
    public FireParameter(){
        this("Simulation Team 7", 25, 25, .15, .7);
    }

    public FireParameter(String fileAuthor, Integer length, Integer width, double prob, double perc){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        probCatch = prob;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.BURNING, State.ALIVE, State.EMPTY));
    }

    public FireCell makeCell(double prob){
        CellState state;
        if(Math.random() < prob){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(0);
        }
        return new FireCell(state);
    }

    public double getThreshold() {
        return probCatch;
    }

    @Override
    public String toString(){
        return "Fire Simulation";
    }
}
