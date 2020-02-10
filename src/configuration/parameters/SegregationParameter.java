package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.CellState;
import cellsociety.cellstate.SegregationCell;
import cellsociety.cellstate.State;
import simulation.SegregationSimulation;
import simulation.Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class SegregationParameter extends Parameter {
    private double tolerance;
    private double percentEmpty;

    public SegregationParameter(){
        this("Square", 100, 100, .30, .30, .20);
    }

    public SegregationParameter(String type, Integer length, Integer width, double percentTol, double percentMinority, double percentOpen){
        gridType = type;
        tolerance = percentTol;
        gridLength = length;
        gridWidth = width;
        percentage = percentMinority;
        percentEmpty = percentOpen;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.MINORITY, State.EMPTY, State.MAJORITY));
    }

    public SegregationCell makeCell(){
        CellState state;
        double roll = Math.random();
        if(roll < percentage){
            state = possibleStates.get(0);
        }
        else if(roll < percentEmpty + percentage){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(2);
        }
        return new SegregationCell(state);
    }

    public double getThreshold() {
        return tolerance;
    }

    public Simulation makeSimulation(ArrayList<ArrayList<Cell>> initialGrid, Parameter currentParam){
        return new SegregationSimulation(initialGrid, currentParam);
    }


    @Override
    public String toString(){
        return "Segregation Simulation";
    }
}
