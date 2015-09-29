



import java.util.List;

/**
 * Contains rules and variables for the Game of Life simulation.
 */
public class GameOfLifeSimulation extends Simulation {
    private int myMinNeighborsToLive, myMaxNeighborsToLive, myNeighborsToReproduce;

    /**
     * Instantiates a new game of life simulation.
     *
     * @param cellSocietyGrid the cell society grid
     * @param parameters the parameters
     */
    public GameOfLifeSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        myMinNeighborsToLive = Integer.parseInt(parameters[0]);
        myMaxNeighborsToLive = Integer.parseInt(parameters[1]);
        myNeighborsToReproduce = Integer.parseInt(parameters[2]);
    }

    /* (non-Javadoc)
     * @see Simulation#processNeighbors(Cell, int, int)
     */
    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        int aliveNeighbors = 0;
        for (Cell c : neighbors) {
            if (c.getCurrentState() == GameOfLifeCell.ALIVE) {
                aliveNeighbors++;
            }
        }
        if (currentCell.getCurrentState() == GameOfLifeCell.ALIVE &&
            (aliveNeighbors < myMinNeighborsToLive || aliveNeighbors > myMaxNeighborsToLive)) {
            currentCell.setFutureState(Cell.EMPTY);
        }
        if (currentCell.getCurrentState() == Cell.EMPTY &&
            (aliveNeighbors == myNeighborsToReproduce)) {
            currentCell.setFutureState(GameOfLifeCell.ALIVE);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return "GameOfLifeSimulation [myMinNeighborsToLive=" + myMinNeighborsToLive +
               ", myMaxNeighborsToLive=" + myMaxNeighborsToLive + ", myNeighborsToReproduce=" +
               myNeighborsToReproduce + "]";
    }

}
