package cellsociety.cellstate;

import cellsociety.Cell;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatorCell extends Cell implements CellState {
    private int energy;
    private int birth;
    private List<CellState> possibleStates;

    public WatorCell(CellState myState){
        super(myState);
        energy = 5;
        birth = 7;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.DEAD, State.PREY, State.PREDATOR));
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

    @Override
    public CellState getNextState(){
        if(getState() == State.EMPTY){
            return State.EMPTY;
        }
        decrementBirth();
        decrementEnergy();
        if(energy<=0 && getState() == State.PREDATOR){
            return State.DEAD;
        }
        if(birth>0){
            return State.MOVE;
        }
        else{
            birth = 7;
        }
        return getState();
    }
    public Paint getColor(){
        return Color.GREEN;
    }

    @Override
    public Cell clone() {
        return new WatorCell(this.state);
    }
}
