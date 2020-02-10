package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import cellsociety.cellstate.GameOfLifeCell;
import cellsociety.cellstate.State;
import simulation.GameOfLifeSimulation;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeParameter extends Parameter {
    private double threshold;


    public GameOfLifeParameter(){
        this("Square", 100, 100, .30);
    }

    public GameOfLifeParameter(String type, Integer length, Integer width, double percentAlive){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        percentage = percentAlive;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.DEAD, State.ALIVE, State.EMPTY));
    }

    public GameOfLifeCell makeCell(){
        CellState state;
        if(Math.random() < percentage){
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

    public Simulation makeSimulation(ArrayList<ArrayList<Cell>> initialGrid, Parameter currentParam){
        return new GameOfLifeSimulation(initialGrid, currentParam);
    }



    @Override
    public String toString(){
        return "Game of Life Simulation";
    }
}
