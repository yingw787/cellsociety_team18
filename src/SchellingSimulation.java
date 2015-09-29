

import java.util.List;


/**
 * Hold the rules and variables for the segregation (or Schelling) simulation.
 */
public class SchellingSimulation extends Simulation {
    private double mySatisfactionThreshold;

    /**
     * Instantiates a new Schelling simulation.
     *
     * @param cellSocietyGrid the cell society grid
     * @param parameters the parameters
     */
    public SchellingSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        this.mySatisfactionThreshold = Double.parseDouble(parameters[0]);
    }

    /* (non-Javadoc)
     * @see Simulation#processNeighbors(Cell, int, int)
     */
    @Override
    public void processNeighbors (Cell currentCell, int x, int y) {
        if (currentCell.getCurrentState() != Cell.EMPTY) {
            List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
            double satisfactionNumber = 0;
            double totalNonEmptyNeighbors = 0;
            for (Cell c : neighbors) {
                if (currentCell.getCurrentState() == c.getCurrentState()) {
                    satisfactionNumber += 1;
                }
                if (c.getCurrentState() != Cell.EMPTY) {
                    totalNonEmptyNeighbors += 1;
                }
            }
            if ((totalNonEmptyNeighbors != 0) &&
                ((satisfactionNumber / totalNonEmptyNeighbors) < mySatisfactionThreshold)) {
                findAndUpdateFutureStates(currentCell);
            }
        }
    }

    /**
     * Find and update future states.
     *
     * @param currentCell the current cell
     */
    public void findAndUpdateFutureStates (Cell currentCell) {
        Cell emptyCell = getCellSocietyGrid().dequeueRandomGlobalEmpty();
        if (emptyCell != null) { // Implementation: randomly selects and removes from emptycell
                                 // array, then adds new one to end of array
            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getCurrentState());
            getCellSocietyGrid().makeStateEmpty(currentCell);
        }
    }
}
