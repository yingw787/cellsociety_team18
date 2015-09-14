import java.util.ArrayList;

public class SchellingSimulation extends Simulation{
    private double MY_SATISFACTION_THRESHOLD;

    public SchellingSimulation(double satisfactionThreshold) {
        MY_SATISFACTION_THRESHOLD=satisfactionThreshold;
    }

    @Override
    public void checkNeighbors() {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                SchellingCell currentCell = (SchellingCell)getCellSocietyGrid().getMyCells()[i][j];
                if (currentCell.getMyCurrentState()!=Cell.EMPTY) {
                    ArrayList<Cell> neighbors = getCellSocietyGrid().getNeighbors(i,j);
                    int satisfactionNumber = 0;
                    for (Cell c : neighbors) {
                        if (currentCell.getMyCurrentState()==c.getMyCurrentState()) {
                            satisfactionNumber+=1;
                        }
                    }
                    if ((satisfactionNumber/neighbors.size())<MY_SATISFACTION_THRESHOLD) {
                        Cell emptyCell = getCellSocietyGrid().dequeueNextGlobalEmpty();
                        if (emptyCell!=null) { //Implementation: moves to the next empty cell in the queue (not random)
                            getCellSocietyGrid().changeEmptyState(emptyCell, currentCell.getMyCurrentState());
                            getCellSocietyGrid().makeStateEmpty(currentCell);
                        }
                    }
                }
            }
        }
    }
}
