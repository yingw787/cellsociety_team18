import java.util.ArrayList;
import java.util.Iterator;

public class FishCell extends FishSharkCell{
    public static final int FISH = 1;
    public FishCell (int x, int y) {
        super(FISH, x, y);
    }
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        Iterator<Cell> iter = neighbors.iterator();
        while (iter.hasNext()) {
            Cell c = iter.next();
            if (c.getMyFutureState()!=Cell.EMPTY) {
                iter.remove();
                //System.out.println("future state: "+c.getMyFutureState());
            }
        }
        System.out.println("neighbor size of " + getMyXCoordinate() + ", "+getMyYCoordinate()+": "+neighbors.size());
        if (neighbors.size()!=0) {
            Cell swapee = neighbors.get((int)(Math.random()*neighbors.size()));
            return swapee;
        }
        return null;
    }
}
