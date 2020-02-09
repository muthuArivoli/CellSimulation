package configuration.parameters;

import cellsociety.cellstate.CellState;
import cellsociety.cellstate.FireCell;
import cellsociety.cellstate.GameOfLifeCell;
import cellsociety.cellstate.State;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeParameter extends Parameter {
    private double threshold;


    public GameOfLifeParameter(){
        this("Simulation Team 7", 25, 25, .7);
    }

    public GameOfLifeParameter(String type, Integer length, Integer width, double perc){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.ALIVE, State.DEAD, State.EMPTY));
    }

    public GameOfLifeCell makeCell(double prob){
        CellState state;
        if(Math.random() < prob){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(0);
        }
        return new GameOfLifeCell(state);
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Game of Life Simulation";
    }
}
