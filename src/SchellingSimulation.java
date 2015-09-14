import java.util.ArrayList;

public class SchellingSimulation extends Simulation{
    private double MY_SATISFACTION_THRESHOLD;

    public SchellingSimulation(double satisfactionThreshold) {
        MY_SATISFACTION_THRESHOLD=satisfactionThreshold;
    }

    @Override
    public void processNeighbors (Cell currentCell, ArrayList<Cell> neighbors) {
        double satisfactionNumber = 0;
        double totalNonEmptyNeighbors = 0;
        for (Cell c : neighbors) {
            if (currentCell.getMyCurrentState()==c.getMyCurrentState()) {
                satisfactionNumber+=1;
            }
            if (c.getMyCurrentState()!=Cell.EMPTY) {
                totalNonEmptyNeighbors+=1;
            }
        }
        if ((totalNonEmptyNeighbors!=0) && ((satisfactionNumber/totalNonEmptyNeighbors)<MY_SATISFACTION_THRESHOLD)) {
            findAndUpdateFutureStates(currentCell);
        }
    }

    @Override
    public void findAndUpdateFutureStates (Cell currentCell) {
        Cell emptyCell = getCellSocietyGrid().dequeueRandomGlobalEmpty();
        if (emptyCell!=null) { //Implementation: randomly selects and removes from emptycell array, then adds new one to end of array
            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getMyCurrentState());
            getCellSocietyGrid().makeStateEmpty(currentCell);
        }
    }
}
