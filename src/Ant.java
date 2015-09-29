
/**
 * Represents an Ant object which shares properties with the Cell class.
 *
 * @author John
 */
public class Ant extends CellWithAngleAndPatch{
    private boolean hasFood;
    
    /**
     * Instantiates a new ant.
     *
     * @param parameters the parameters
     */
    public Ant (String[] parameters) {
        super(parameters);
        setHasFood(false);
    }
    
    /**
     * Leave food pheromone.
     *
     * @param cell the cell
     * @param threshold the threshold
     * @param amount the amount
     */
    public void leaveFoodPheromone(AntSpaceCell cell, int threshold, int amount) {
        leavePheromone(AntSpaceCell.FOODINDEX, cell, threshold, amount);
    }
    
    /**
     * Leave home pheromone.
     *
     * @param cell the cell
     * @param threshold the threshold
     * @param amount the amount
     */
    public void leaveHomePheromone(AntSpaceCell cell, int threshold, int amount) {
        leavePheromone(AntSpaceCell.HOMEINDEX, cell, threshold, amount);
    }
    
    /**
     * Leaves the specified type of pheromone if it doesn't already exceed threshold.
     *
     * @param patchIndex the patch index
     * @param cell the cell
     * @param threshold the threshold
     * @param amount the amount
     */
    private void leavePheromone(int patchIndex, AntSpaceCell cell, int threshold, int amount) {
        if (cell.getFuturePatch().get(patchIndex) < threshold) {
            cell.getFuturePatch().set(patchIndex,cell.getFuturePatch().get(patchIndex)+amount);
        }
    }
    
    /**
     * Checks for food.
     *
     * @return true, if successful
     */
    public boolean hasFood () {
        return hasFood;
    }
    
    /**
     * Sets the check for food.
     *
     * @param hasFood the new value
     */
    public void setHasFood (boolean hasFood) {
        this.hasFood = hasFood;
    }
}
