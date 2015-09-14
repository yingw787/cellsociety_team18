import java.util.ArrayList;

public class SchellingSimulation extends Simulation{
    private double MY_SATISFACTION_THRESHOLD;

    public SchellingSimulation(double satisfactionThreshold) {
        MY_SATISFACTION_THRESHOLD=satisfactionThreshold;
    }

    public void processNeighbors (Cell currentCell, ArrayList<Cell> neighbors) {
        int satisfactionNumber = 0;
        for (Cell c : neighbors) {
            if (currentCell.getMyCurrentState()==c.getMyCurrentState()) {
                satisfactionNumber+=1;
            }
        }
        if ((satisfactionNumber/neighbors.size())<MY_SATISFACTION_THRESHOLD) {
            findAndUpdateFutureStates(currentCell);
        }
    }

    public void findAndUpdateFutureStates (Cell currentCell) {
        Cell emptyCell = getCellSocietyGrid().dequeueNextGlobalEmpty();
        if (emptyCell!=null) { //Implementation: moves to the next empty cell in the queue (not random)
            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getMyCurrentState());
            getCellSocietyGrid().makeStateEmpty(currentCell);
        }
    }
}
