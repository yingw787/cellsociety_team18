
public class FishSharkCell extends Cell {
    private int myCurrentSteps;
    private FishSharkCell mySwapee;
    public FishSharkCell () {
        super(Cell.EMPTY);
    }
    public FishSharkCell (int state) {
        super(state);
    }
    public FishSharkCell getSwapee () {
        return mySwapee;
    }
    public void setSwapee (FishSharkCell swapee) {
        this.mySwapee = swapee;
    }

}
