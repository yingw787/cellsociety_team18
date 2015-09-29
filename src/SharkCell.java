
import java.util.ArrayList;
import java.util.List;


public class SharkCell extends WaterCell {
    public static final int SHARK = 2;
    public SharkCell (String[] parameters) {
        super(parameters);
        setCurrentSteps(0);
    }

    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        List<Cell> fish = new ArrayList<Cell>();
        Cell swapee;
        for (Cell c : neighbors) {
            if (c.getFutureState() == FishCell.FISH) {
                fish.add(c);
            }
        }
        if (fish.size() > 0) {
            swapee = fish.get((int) Math.random() * fish.size());
            System.out.println("eatfish:" + swapee.getXCoordinate() + swapee.getYCoordinate());
            return swapee;
        }
        return getSwapNeighborHelper(neighbors);
    }

    @Override
    public void decrementEnergy () {
        setCurrentEnergy(getCurrentEnergy() - 1);
    }
}
