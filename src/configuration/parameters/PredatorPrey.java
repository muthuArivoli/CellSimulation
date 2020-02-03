package configuration.parameters;

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
        possibleStates = new ArrayList<States>(Arrays.asList(States.BURNING, States.EMPTY, States.TREE));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Predator-Prey Simulation";
    }
}
