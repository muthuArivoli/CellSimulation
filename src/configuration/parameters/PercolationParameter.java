package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.*;
import simulation.FireSimulation;
import simulation.PercolationSimulation;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class PercolationParameter extends Parameter {
    private double threshold;


    public PercolationParameter(){
        this("Simulation Team 7", 25, 25, .7);
    }

    public PercolationParameter(String type, Integer length, Integer width, double perc){
        gridType = type;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.OPEN, State.BLOCKED, State.FULL));
    }

    public PercolationCell makeCell(double prob){
        CellState state;
        if(Math.random() < prob){
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
