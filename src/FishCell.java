import java.util.ArrayList;

public class FishCell extends FishSharkCell{
    public static final int FISH = 1;
    public FishCell (int state) {
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
