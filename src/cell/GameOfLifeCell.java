package cell;

/**
 * A simple cell for the Game of Life simulation
 */
public class GameOfLifeCell extends Cell {
    public static final int ALIVE = 1;

    /**
     * Instantiates a new game of life cell.
     *
     * @param parameters the parameters
     */
    public GameOfLifeCell (String[] parameters) {
        super(parameters);
    }

}
