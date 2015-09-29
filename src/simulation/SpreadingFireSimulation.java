package simulation;

import java.util.List;
import cell.Cell;
import cell.TreeCell;
import grid.GridOfCells;


/**
 * Contains the rules and variables for the spreading fire simulation
 */
public class SpreadingFireSimulation extends Simulation {
    private double mySpreadRate;

    /**
     * Instantiates a new spreading fire simulation.
     *
     * @param cellSocietyGrid the cell society grid
     * @param parameters the parameters
     */
    public SpreadingFireSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        mySpreadRate = Double.parseDouble(parameters[0]);
    }

    /*
     * (non-Javadoc)
     *
     * @see Simulation#processNeighbors(Cell, int, int)
     */
    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        if (currentCell.getCurrentState() == Cell.EMPTY) {
            for (Cell c : neighbors) {
                if (c.getCurrentState() == TreeCell.BURNING) {
                    if (Math.random() < mySpreadRate) {
                        currentCell.setFutureState(TreeCell.BURNING);
                    }
                    break;
                }
            }
        }
        else if (currentCell.getCurrentState() == TreeCell.BURNING) {
            currentCell.setFutureState(TreeCell.DEAD);
        }
    }
}
