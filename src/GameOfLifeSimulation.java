



import java.util.List;


public class GameOfLifeSimulation extends Simulation {
    private int myMinNeighborsToLive, myMaxNeighborsToLive, myNeighborsToReproduce;

    public GameOfLifeSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        myMinNeighborsToLive = Integer.parseInt(parameters[0]);
        myMaxNeighborsToLive = Integer.parseInt(parameters[1]);
        myNeighborsToReproduce = Integer.parseInt(parameters[2]);
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        int aliveNeighbors = 0;
        for (Cell c : neighbors) {
            if (c.getMyCurrentState() == GameOfLifeCell.ALIVE) {
                aliveNeighbors++;
            }
        }
        if (currentCell.getMyCurrentState() == GameOfLifeCell.ALIVE &&
            (aliveNeighbors < myMinNeighborsToLive || aliveNeighbors > myMaxNeighborsToLive)) {
            currentCell.setMyFutureState(Cell.EMPTY);
        }
        if (currentCell.getMyCurrentState() == Cell.EMPTY &&
            (aliveNeighbors == myNeighborsToReproduce)) {
            currentCell.setMyFutureState(GameOfLifeCell.ALIVE);
        }
    }

    @Override
    public String toString () {
        return "GameOfLifeSimulation [myMinNeighborsToLive=" + myMinNeighborsToLive +
               ", myMaxNeighborsToLive=" + myMaxNeighborsToLive + ", myNeighborsToReproduce=" +
               myNeighborsToReproduce + "]";
    }

}
