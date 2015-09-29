
/**
 * Represents the cell for the spreading fire simulation, contains one tree either dead, burning, or alive.
 */
public class TreeCell extends Cell {
    public static final int BURNING = 1;
    public static final int DEAD = 2;

    /**
     * Instantiates a new tree cell.
     *
     * @param parameters the parameters
     */
    public TreeCell (String[] parameters) {
        super(parameters);
    }

}
