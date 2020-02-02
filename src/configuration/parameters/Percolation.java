package configuration.parameters;

import configuration.States;

import java.util.ArrayList;

public class Percolation extends Parameter {
    public Integer gridLength;
    public Integer gridWidth;
    public double threshold;
    public ArrayList<States> possibleStates;

    public Percolation(){
    }

    @Override
    public String toString(){
        return "Percolation Simulation";
    }
}
