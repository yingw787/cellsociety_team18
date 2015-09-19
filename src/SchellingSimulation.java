
import java.util.ArrayList;


public class SchellingSimulation extends Simulation {
    private double mySatisfactionThreshold;

    public SchellingSimulation (GridOfCells cellSocietyGrid, double mySatisfactionThreshold) {
        super(cellSocietyGrid);
        this.mySatisfactionThreshold = mySatisfactionThreshold;
    }

    @Override
    public void processNeighbors (Cell currentCell, int x, int y) {
        if (currentCell.getMyCurrentState() != Cell.EMPTY) {
            ArrayList<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
            double satisfactionNumber = 0;
            double totalNonEmptyNeighbors = 0;
            for (Cell c : neighbors) {
                if (currentCell.getMyCurrentState() == c.getMyCurrentState()) {
                    satisfactionNumber += 1;
                }
                if (c.getMyCurrentState() != Cell.EMPTY) {
                    totalNonEmptyNeighbors += 1;
                }
            }
            if ((totalNonEmptyNeighbors != 0) &&
                ((satisfactionNumber / totalNonEmptyNeighbors) < mySatisfactionThreshold)) {
                findAndUpdateFutureStates(currentCell);
            }
        }
    }

    public void findAndUpdateFutureStates (Cell currentCell) {
        Cell emptyCell = getCellSocietyGrid().dequeueRandomGlobalEmpty();
        if (emptyCell != null) { // Implementation: randomly selects and removes from emptycell
                                 // array, then adds new one to end of array
            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getMyCurrentState());
            getCellSocietyGrid().makeStateEmpty(currentCell);
        }
    }
}
