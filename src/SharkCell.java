import java.util.ArrayList;
import java.util.Iterator;

public class SharkCell extends FishSharkCell{
    public static final int SHARK = 2;
    private int myCurrentEnergy;
    public SharkCell (int x, int y) {
        super(SHARK, x, y);
    }
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        ArrayList<Cell> fish = new ArrayList<Cell>();
        Cell swapee;
        for (Cell c: neighbors) {
            if (c.getMyFutureState()==FishCell.FISH) {
                fish.add(c);
            }
        }
        if (fish.size()>0) {
            swapee = fish.get((int)Math.random()*fish.size());
            return swapee;
        }
        Iterator<Cell> iter = neighbors.iterator();
        while (iter.hasNext()) {
            Cell c = iter.next();
            if (c.getMyFutureState()!=Cell.EMPTY) {
                iter.remove();
            }
        }
        swapee = neighbors.get((int)(Math.random()*neighbors.size()));
        return swapee;
    }
}
