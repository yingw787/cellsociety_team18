
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a cell with a shark for the WaTor simulation
 */
public class SharkCell extends WaterCell {
    public static final int SHARK = 2;
    
    /**
     * Instantiates a new shark cell.
     *
     * @param parameters the parameters
     */
    public SharkCell (String[] parameters) {
        super(parameters);
        setCurrentSteps(0);
    }

    /* (non-Javadoc)
     * @see WaterCell#getSwapNeighbor(java.util.List)
     */
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

    /* (non-Javadoc)
     * @see WaterCell#decrementEnergy()
     */
    @Override
    public void decrementEnergy () {
        setCurrentEnergy(getCurrentEnergy() - 1);
    }
}
