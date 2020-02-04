package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;
import simulation.GameOfLifeSimulation;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeParameter extends Parameter {
    private double threshold;


    public GameOfLifeParameter(){
        this("Simulation Team 7", 25, 25, .7);
    }

    public GameOfLifeParameter(String fileAuthor, Integer length, Integer width, double perc){
        author = fileAuthor;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.ALIVE, States.DEAD, States.EMPTY));
    }

    public double getThreshold(){
        return threshold;
    }

    @Override
    public String toString(){
        return "Game of Life Simulation";
    }
}
