
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


public abstract class Simulation {
    private GridOfCells cellSocietyGrid;

    public Simulation (GridOfCells cellSocietyGrid) {
        this.cellSocietyGrid = cellSocietyGrid;
    }

    public void step () {
        firstPass();
        secondPass();
    }

    public void firstPass () {
        checkNeighbors();
    }

    public void secondPass () {
        updateCurrentStates();
    }

    public void playAndLoop (Timeline timeline, KeyFrame action) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(action);
        timeline.play();
    }

    public void checkNeighbors () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                Cell currentCell = getCellSocietyGrid().getMyCells().get(y).get(x);
                processNeighbors(currentCell, x, y);
            }
        }
    }

    abstract void processNeighbors (Cell currentCell, int x, int y);

    public void updateCurrentStates () {
        for (Cell c: cellSocietyGrid) {
                c.setMyCurrentState(c.getMyFutureState());
            }

    }

    public GridOfCells getCellSocietyGrid () {
        return cellSocietyGrid;
    }

    // debug helper
    public static void print (GridOfCells g) {
        for (int i = 0; i < g.getMyCells().size(); i++) {
            for (int j = 0; j < g.getMyCells().get(0).size(); j++) {
                System.out.print(g.getMyCells().get(i).get(j).getMyCurrentState() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
}
