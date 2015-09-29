package cell;
import java.util.List;

/**
 * The Cell for WaTor Simulation, represents an cell that contains a fish.
 */
public class FishCell extends WaterCell {
    public static final int FISH = 1;

    /**
     * Instantiates a new fish cell.
     *
     * @param x the x
     * @param y the y
     */
    public FishCell (int x, int y) {
        super(new String[]{new Integer(FISH).toString(), new Integer(x).toString(), new Integer(y).toString()});
        setCurrentSteps(0);
        setCurrentEnergy(1);
    }
    
    /**
     * Instantiates a new fish cell.
     *
     * @param parameters the parameters
     */
    public FishCell (String parameters[]) {
        super(parameters);
        setCurrentSteps(0);
        setCurrentEnergy(1);
    }

    /* (non-Javadoc)
     * @see FishSharkCell#getSwapNeighbor(java.util.List)
     */
    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return (getSwapNeighborHelper(neighbors));
    }

}
