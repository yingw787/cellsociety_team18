
import java.util.ArrayList;
import java.util.List;


public class SharkCell extends FishSharkCell {
    public static final int SHARK = 2;

    public SharkCell (int x, int y, int initialEnergy) {
        super(SHARK, x, y);
        setMyCurrentSteps(0);
        setMyCurrentEnergy(initialEnergy);
    }

    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        List<Cell> fish = new ArrayList<Cell>();
        Cell swapee;
        for (Cell c : neighbors) {
            if (c.getMyFutureState() == FishCell.FISH) {
                fish.add(c);
            }
        }
        if (fish.size() > 0) {
            swapee = fish.get((int) Math.random() * fish.size());
            System.out.println("eatfish:" + swapee.getMyXCoordinate() + swapee.getMyYCoordinate());
            return swapee;
        }
        return getSwapNeighborHelper(neighbors);
    }

    @Override
    public void decrementEnergy () {
        setMyCurrentEnergy(getMyCurrentEnergy() - 1);
    }
}
