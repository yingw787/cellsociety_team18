import java.util.ArrayList;
import java.util.Iterator;

public class FishCell extends FishSharkCell{
    public static final int FISH = 1;
    public FishCell (int x, int y) {
        super(FISH, x, y);
    }
    @Override
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        return(getSwapNeighborHelper(neighbors));
    }
}
