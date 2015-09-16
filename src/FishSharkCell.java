import java.util.ArrayList;
import java.util.Iterator;

public class FishSharkCell extends Cell {
    private int myCurrentSteps;

    public FishSharkCell (int x, int y) {
        super(Cell.EMPTY, x, y);
    }
    public FishSharkCell (int state, int x, int y) {
        super(state, x ,y);
    }
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        return null;
    }
    protected Cell getSwapNeighborHelper(ArrayList<Cell> neighbors) {
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
