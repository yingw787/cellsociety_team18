import java.util.ArrayList;

public class FishCell extends FishSharkCell{
    public static final int FISH = 1;
    public FishCell (int x, int y) {
        super(FISH, x, y);
    }
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        for (Cell c: neighbors) {
            if (c.getMyFutureState()!=Cell.EMPTY) {
                neighbors.remove(c);
                System.out.println("future state: "+c.getMyFutureState());
            }
        }
        System.out.println("neighbor size: "+neighbors.size());
        Cell swapee = neighbors.get((int)Math.random()*neighbors.size());
        return swapee;
    }
}
