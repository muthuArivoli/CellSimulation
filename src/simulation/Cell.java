package simulation;

public class Cell implements Clonable {

    private int state;
    public Cell(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public Cell clone() {
        return new Cell(this.state);
    }
}
