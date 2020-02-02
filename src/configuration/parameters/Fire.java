package configuration.parameters;

import configuration.States;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Fire extends Parameter {
    public Integer gridLength;
    public Integer gridWidth;
    public double probCatch;
    public ArrayList<States> possibleStates;
    //public ArrayList<ArrayList<Cell>> initialGrid;

    public Fire(){
        gridLength = 25;
        gridWidth = 25;
        probCatch = .25;
        possibleStates = new ArrayList<States>(Arrays.asList(States.BURNING, States.EMPTY, States.TREE));
        createGrid();
    }

    //public Fire(File file){
//        readFile();
//    }

    private void createGrid(){
    }

    //public Collection getGrid(){
        //return myGrid;
    //}


    @Override
    public String toString(){
        return "Fire Simulation";
    }
}
