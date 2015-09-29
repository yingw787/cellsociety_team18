package simulation;

import cell.Cell;
import grid.GridOfCells;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


/**
 * The simulation super class, hold the skeleton of what all simulations should at least contain and implement.
 */
public abstract class Simulation {
    private GridOfCells cellSocietyGrid;

    /**
     * Instantiates a new simulation.
     *
     * @param cellSocietyGrid the cell society grid
     */
    public Simulation (GridOfCells cellSocietyGrid) {
        this.cellSocietyGrid = cellSocietyGrid;
    }

    /**
     * The main timeline loop to advance the simulation.
     */
    public void step () {
        firstPass();
        secondPass();
    }

    /**
     * First pass.
     */
    public void firstPass () {
        checkNeighbors();
    }

    /**
     * Second pass.
     */
    public void secondPass () {
        updateCurrentStates();
    }

    /**
     * Play and loop.
     *
     * @param timeline the timeline
     * @param action the action
     */
    public void playAndLoop (Timeline timeline, KeyFrame action) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(action);
        timeline.play();
    }

    /**
     * Loops through reach cell in the grid and check neighbors.
     */
    public void checkNeighbors () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                Cell currentCell = getCellSocietyGrid().getCells().get(y).get(x);
                processNeighbors(currentCell, x, y);
            }
        }
    }

    /**
     * Process neighbors.
     *
     * @param currentCell the current cell
     * @param x the x
     * @param y the y
     */
    abstract void processNeighbors (Cell currentCell, int x, int y);

    /**
     * Update current states, typically done in the second pass.
     */
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                getCellSocietyGrid().getCells().get(y).get(x)
                        .setCurrentState(getCellSocietyGrid().getCells().get(y).get(x)
                                .getFutureState());
            }
        }
    }

    /**
     * Gets the cell society grid.
     *
     * @return the cell society grid
     */
    public GridOfCells getCellSocietyGrid () {
        return cellSocietyGrid;
    }

    /**
     * Prints Grid. Debugging helper.
     *
     * @param grid the Grid
     */
    public static void print (GridOfCells grid) {
        for (int i = 0; i < grid.getCells().size(); i++) {
            for (int j = 0; j < grid.getCells().get(0).size(); j++) {
                System.out.print(grid.getCells().get(i).get(j).getCurrentState() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    
}
