package configuration.parameters;

import cellsociety.CellStates;
import configuration.States;

import java.util.ArrayList;
import java.util.Arrays;

public class WaTorParameter extends Parameter {
    private double probEaten;

    public WaTorParameter(){
        this("Simulation Team 7", 25, 25, .25, .7);
    }


    public WaTorParameter(String fileAuthor, Integer length, Integer width, Double prob, Double perc){
        author = fileAuthor;
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellStates>(Arrays.asList(States.DEAD, States.PREY, States.PREDATOR));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Wa Tor Simulation";
    }
}
