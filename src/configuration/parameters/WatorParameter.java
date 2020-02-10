package configuration.parameters;

import cellsociety.Cell;
import cellsociety.cellstate.*;
import simulation.Simulation;
import simulation.WatorSimulation;

import java.util.ArrayList;
import java.util.Arrays;

public class WatorParameter extends Parameter {
    private int energy;
    private int birthRate;
    private double percPredator;

    public WatorParameter(){
        this("Square", 100,100,.30,.30,3,7);
    }

    public WatorParameter(String type, Integer length, Integer width, Double percentPrey, Double percentPredator, int energyStart, int birth){
        gridType = type;
        energy = energyStart;
        birthRate = birth;
        gridLength = length;
        gridWidth = width;
        percentage = percentPrey;
        percPredator = percentPredator;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.PREY, State.PREDATOR, State.EMPTY, State.DEAD));
    }

    public WatorCell makeCell(){
        CellState state;
        double roll = Math.random();
        if(roll < percentage) {
            state = possibleStates.get(0);
        }
        else if(roll < percentage + percPredator){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(2);
        }
        return new WatorCell(state, energy, birthRate);
    }

    public double getThreshold() {
        return birthRate;
    }

    public Simulation makeSimulation(ArrayList<ArrayList<Cell>> initialGrid, Parameter currentParam){
        return new WatorSimulation(initialGrid, currentParam);
    }

    @Override
    public String toString(){
        return "Wator Simulation";
    }
}
