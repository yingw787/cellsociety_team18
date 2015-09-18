import java.util.ArrayList;

public class GameOfLifeSimulation extends Simulation{
    private int myMinNeighborsToLive, myMaxNeighborsToLive, myNeighborsToReproduce;
    public GameOfLifeSimulation(int min, int max, int reproduce) {
        super();
        myMinNeighborsToLive=min;
        myMaxNeighborsToLive=max;
        myNeighborsToReproduce=reproduce;
    }
    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        ArrayList<Cell> neighbors = getCellSocietyGrid().getNeighbors(x,y);
            int aliveNeighbors=0;
            for (Cell c : neighbors) {
                if (c.getMyCurrentState()==GameOfLifeCell.ALIVE){
                    aliveNeighbors++;
                }
            }
            if (currentCell.getMyCurrentState()==GameOfLifeCell.ALIVE&&(aliveNeighbors<myMinNeighborsToLive || aliveNeighbors>myMaxNeighborsToLive)) {
                currentCell.setMyFutureState(Cell.EMPTY);
            }
            if (currentCell.getMyCurrentState()==Cell.EMPTY&&(aliveNeighbors==myNeighborsToReproduce)) {
                currentCell.setMyFutureState(GameOfLifeCell.ALIVE);
            }
    }

}
