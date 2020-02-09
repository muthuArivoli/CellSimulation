package configuration.parameters;

import cellsociety.cellstate.*;

import java.util.ArrayList;
import java.util.Arrays;

public class WatorParameter extends Parameter {
    private double probEaten;

    public WatorParameter(){
        this("Simulation Team 7", 25, 25, .25, .7);
    }


    public WatorParameter(String fileAuthor, Integer length, Integer width, Double prob, Double perc){
        author = fileAuthor;
        probEaten = prob;
        gridLength = length;
        gridWidth = width;
        percentage = perc;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.PREY, State.PREDATOR, State.DEAD));
    }

    public WatorCell makeCell(double prob){
        CellState state;
        double roll = Math.random();
        if(roll > prob + (1 - prob)/2){
            state = possibleStates.get(0);
        }
        else if(roll > prob){
            state = possibleStates.get(1);
        }
        else{
            state = possibleStates.get(2);
        }
        return new WatorCell(state);
    }

    public double getThreshold() {
        return probEaten;
    }

    @Override
    public String toString(){
        return "Wa Tor Simulation";
    }
}
