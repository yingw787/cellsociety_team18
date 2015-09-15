import java.util.ArrayList;

public class SharkCell extends FishSharkCell{
    public static final int SHARK = 1;
    private int myCurrentEnergy;
    public SharkCell (int state) {
        super(state);
    }
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        for (Cell c: neighbors) {
            if (c.getMyFutureState()!=Cell.EMPTY) {
                neighbors.remove(c);
            }
        }
        Cell swapee = neighbors.get((int)Math.random()*neighbors.size());
        return swapee;
    }
}
