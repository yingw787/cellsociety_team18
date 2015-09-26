

import java.util.ArrayList;
import java.util.List;


public class SpreadingFireSimulation extends Simulation {
    private double mySpreadRate;

    public SpreadingFireSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        mySpreadRate = Double.parseDouble(parameters[0]);
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        if (currentCell.getMyCurrentState() == TreeCell.HEALTHY) {
            for (Cell c : neighbors) {
                if (c.getMyCurrentState() == TreeCell.BURNING) {
                    if (Math.random() < mySpreadRate) {
                        currentCell.setMyFutureState(TreeCell.BURNING);
                    }
                    break;
                }
            }
        }
        else if (currentCell.getMyCurrentState() == TreeCell.BURNING) {
            currentCell.setMyFutureState(Cell.EMPTY);
        }
    }

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }

}
