

import java.util.List;


public class SchellingSimulation extends Simulation {
    private double mySatisfactionThreshold;

    public SchellingSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        this.mySatisfactionThreshold = Double.parseDouble(parameters[0]);
    }

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

    public void findAndUpdateFutureStates (Cell currentCell) {
        Cell emptyCell = getCellSocietyGrid().dequeueRandomGlobalEmpty();
        if (emptyCell != null) { // Implementation: randomly selects and removes from emptycell
                                 // array, then adds new one to end of array
            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getCurrentState());
            getCellSocietyGrid().makeStateEmpty(currentCell);
        }
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }
}
