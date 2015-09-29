
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
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                Cell currentCell = getCellSocietyGrid().getCells().get(y).get(x);
                processNeighbors(currentCell, x, y);
            }
        }
    }

    abstract void processNeighbors (Cell currentCell, int x, int y);

    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                getCellSocietyGrid().getCells().get(y).get(x)
                        .setCurrentState(getCellSocietyGrid().getCells().get(y).get(x)
                                .getFutureState());
            }
        }
    }

    public GridOfCells getCellSocietyGrid () {
        return cellSocietyGrid;
    }

    // debug helper
    public static void print (GridOfCells g) {
        for (int i = 0; i < g.getCells().size(); i++) {
            for (int j = 0; j < g.getCells().get(0).size(); j++) {
                System.out.print(g.getCells().get(i).get(j).getCurrentState() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
}
