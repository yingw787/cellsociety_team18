
public class FishSharkCell extends Cell {
    private int myCurrentSteps;
    private FishSharkCell mySwapee;
    public FishSharkCell (int x, int y) {
        super(Cell.EMPTY, x, y);
    }
    public FishSharkCell (int state, int x, int y) {
        super(state, x ,y);
    }
    public FishSharkCell getSwapee () {
        return mySwapee;
    }
    public void setSwapee (FishSharkCell swapee) {
        this.mySwapee = swapee;
    }

}
