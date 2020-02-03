package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;
import simulation.GameOfLifeSimulation;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeParameter extends Parameter {
    private double threshold;

    public GameOfLifeParameter(){
        this("Simulation Team 7", 25, 25, 0);
    }

    public GameOfLifeParameter(String fileAuthor, Integer length, Integer width, double thresh){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        threshold = thresh;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.OPEN, States.BLOCKED, States.FULL));
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Game of Life Simulation";
    }
}
