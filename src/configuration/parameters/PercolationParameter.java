package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.*;
import simulation.PercolationSimulation;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class PercolationParameter extends Parameter {
    private double threshold;


    public PercolationParameter(){
        this("Square", 100, 100, .60);
    }

    public PercolationParameter(String type, Integer length, Integer width, double probOpen){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        percentage = probOpen;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.BLOCKED, State.OPEN, State.FULL));
    }

    public PercolationCell makeCell(){
        CellState state;
        if(Math.random() < percentage){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(0);
        }
        return new PercolationCell(state);
    }

    public double getThreshold(){
        return threshold;
    }

    public Simulation makeSimulation(ArrayList<ArrayList<Cell>> initialGrid, Parameter currentParam){
        return new PercolationSimulation(initialGrid, currentParam);
    }


    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
