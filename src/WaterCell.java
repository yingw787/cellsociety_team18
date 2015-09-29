import java.util.Iterator;
import java.util.List;


/**
 * Represents a cell containing only water for the WaTor simulation, and is extended by FishCell and SharkCell
 */
public class WaterCell extends Cell {
    private int myCurrentSteps, myCurrentEnergy;

    /**
     * Instantiates a new fish shark cell.
     *
     * @param x the x
     * @param y the y
     */
    public WaterCell (int x, int y) {
        super(new String[]{new Integer(Cell.EMPTY).toString(), new Integer(x).toString(), new Integer(y).toString()});
    }

    /**
     * Instantiates a new fish shark cell.
     *
     * @param parameters the parameters
     */
    public WaterCell (String[] parameters) {
        super(parameters);
    }

    /* (non-Javadoc)
     * @see Cell#getSwapNeighbor(java.util.List)
     */
    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return null;
    }

    /**
     * Gets the swap neighbor helper.
     *
     * @param neighbors the neighbors
     * @return the swap neighbor helper
     */
    protected Cell getSwapNeighborHelper (List<Cell> neighbors) {
        Iterator<Cell> iter = neighbors.iterator();
        while (iter.hasNext()) {
            Cell c = iter.next();
            if (c.getFutureState() != Cell.EMPTY) {
                iter.remove();
            }
        }
        if (neighbors.size() != 0) {
            Cell swapee = neighbors.get((int) (Math.random() * neighbors.size()));
            return swapee;
        }
        return null;
    }

    /**
     * Gets the current steps.
     *
     * @return the current steps
     */
    public int getCurrentSteps () {
        return myCurrentSteps;
    }

    /**
     * Sets the current steps.
     *
     * @param currentSteps the new current steps
     */
    public void setCurrentSteps (int currentSteps) {
        this.myCurrentSteps = currentSteps;
    }

    /**
     * Gets the current energy.
     *
     * @return the current energy
     */
    public int getCurrentEnergy () {
        return myCurrentEnergy;
    }

    /**
     * Sets the current energy.
     *
     * @param currentEnergy the new current energy
     */
    public void setCurrentEnergy (int currentEnergy) {
        this.myCurrentEnergy = currentEnergy;
    }



    /**
     * Decrement energy.
     */
    public void decrementEnergy () {
        return;
    }
}
