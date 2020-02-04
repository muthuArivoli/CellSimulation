package configuration;

import cellsociety.CellStates;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class WatorState implements CellStates {
    private States myState;
    private int energy;
    private int birth;
    public WatorState(States myState){
        this.myState = myState;
        energy = 5;
        birth = 7;
    }
    public int getEnergy(){
        return energy;
    }

    public int getBirth() {
        return birth;
    }
    public void decrementBirth(){
        birth--;
    }
    public void decrementEnergy(){
        energy--;
    }
    public States getState(){
        return myState;
    }
    public Paint getColor(){
        return Color.GREEN;
    }
}
