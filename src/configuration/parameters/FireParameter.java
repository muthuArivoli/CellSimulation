package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import cellsociety.cellstate.FireCell;
import cellsociety.cellstate.State;
import simulation.FireSimulation;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class FireParameter extends Parameter {
    private double probCatch;

    public FireParameter(){
        this("Square", 100, 100, .70, .70, .30);
    }

    public FireParameter(String type, Integer length, Integer width, double percentCatch, double percentAlive, double percentBurning){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        probCatch = 1 - percentCatch;
        percentage = percentAlive;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.BURNING, State.ALIVE, State.EMPTY));
    }

    public FireCell makeCell(){
        CellState state;
        if(Math.random() < percentage){
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

    public Simulation makeSimulation(ArrayList<ArrayList<Cell>> initialGrid, Parameter currentParam){
        return new FireSimulation(initialGrid, currentParam);
    }


    @Override
    public String toString(){
        return "Fire Simulation";
    }
}
