package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class PredatorPrey extends Parameter {
    private double probEaten;

    public PredatorPrey(){
        this(25, 25, .25);
    }


    public PredatorPrey(Integer length, Integer width, Double prob){
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.DEAD, States.PREY, States.PREDATOR));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Predator-Prey Simulation";
    }
}
