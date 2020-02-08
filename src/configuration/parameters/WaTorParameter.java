package configuration.parameters;

import cellsociety.CellStates;
import configuration.State;

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
        possibleStates = new ArrayList<CellStates>(Arrays.asList(State.DEAD, State.PREY, State.PREDATOR));
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Wa Tor Simulation";
    }
}
