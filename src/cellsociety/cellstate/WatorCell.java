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
    private int birthRate;

    public WatorCell(CellState myState, int energyStart, int birthRateGiven){
        super(myState);
        energy = energyStart;
        birth = birthRateGiven;
        birthRate = birthRateGiven;
        possibleStates = new ArrayList<CellState>(Arrays.asList(State.DEAD, State.PREY, State.PREDATOR));
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
            birth = birthRate;
        }
        return getState();
    }
    public Paint getColor(){
        return Color.GREEN;
    }

    @Override
    public Cell clones() {
        return new WatorCell(this.state, energy, birth);
    }
}
