import java.util.ArrayList;
import java.util.List;

/**
 * Represents the discrete cell locations for the Ant Simulation.
 */
public class AntSpaceCell extends CellWithAngleAndPatch{
    public static final int HOME=1;
    public static final int FOOD=2;
    public static final int HOMEINDEX=0;
    public static final int FOODINDEX=1;
    private List<Ant> myAnts;
    private List<Ant> myFutureAnts;
    
    /**
     * Instantiates a new ant space cell.
     *
     * @param parameters the parameters
     */
    public AntSpaceCell (String[] parameters) {
        super(parameters);
        getPatch().add(Integer.parseInt(parameters[Cell.PATCH_1_PARAMETER_INDEX]));
        getPatch().add(Integer.parseInt(parameters[Cell.PATCH_2_PARAMETER_INDEX]));
        getFuturePatch().add(Integer.parseInt(parameters[Cell.PATCH_1_PARAMETER_INDEX]));
        getFuturePatch().add(Integer.parseInt(parameters[Cell.PATCH_2_PARAMETER_INDEX]));
        initializeAnts(Integer.parseInt(parameters[Cell.NUM_ANT_PARAMETER_INDEX]));
    }
    
    /**
     * Initialize ants.
     *
     * @param numAnts the number of ants
     */
    private void initializeAnts (int numAnts) {
        myAnts=new ArrayList<Ant>();
        myFutureAnts=new ArrayList<Ant>();
        for (int i=0; i<numAnts; i++) {
            Ant a = new Ant(new String[]{"0","0","0","0","0","0"});
            myAnts.add(a);
            myFutureAnts.add(a);
        }
    }
    
    /**
     * Gets the pheromones list.
     *
     * @return the pheromones
     */
    public List<Integer> getPheromones () {
        return getPatch();
    }
    
    /**
     * Gets the future pheromones list.
     *
     * @return the future pheromones
     */
    public List<Integer> getFuturePheromones () {
        return getFuturePatch();
    }
    
    /**
     * Sets the pheromones list.
     *
     * @param pheromones the new pheromones
     */
    public void setPheromones (List<Integer> pheromones) {
        setPatch(pheromones);
    }
    
    /**
     * Sets the future pheromones list.
     *
     * @param pheromones the new future pheromones
     */
    public void setFuturePheromones (List<Integer> pheromones) {
        setFuturePatch(pheromones);
    }

    /**
     * Gets the current ants list.
     *
     * @return the current ants
     */
    public List<Ant> getCurrentAnts () {
        return myAnts;
    }
    
    /**
     * Gets the future ants list.
     *
     * @return the future ants
     */
    public List<Ant> getFutureAnts () {
        return myFutureAnts;
    }
    
    /**
     * Adds a future ant.
     *
     * @param ant the ant
     */
    public void addFutureAnt (Ant ant) {
        myFutureAnts.add(ant);
    }
    
    /**
     * Delete a future ant.
     *
     * @param ant the ant
     */
    public void deleteFutureAnt (Ant ant) {
        try {
            myFutureAnts.remove(ant);
        }
        catch (Exception e) {
        }
    }
    
    /**
     * Sets the current ants list.
     *
     * @param list the new current ants
     */
    public void setCurrentAnts (List<Ant> list) {
        myAnts = list;
    }
    
    /**
     * Sets the future ants list.
     *
     * @param list the new future ants
     */
    public void setFutureAnts (List<Ant> list) {
        myAnts = list;
    }
}
