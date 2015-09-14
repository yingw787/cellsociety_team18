import java.util.ArrayList;

public abstract class Simulation {
    private GridOfCells cellSocietyGrid;
    public void step() {
        firstPass();
        secondPass();
    }
    public void firstPass() {
        checkNeighbors();
    }
    public void secondPass() {
        updateCurrentStates();
    }

    public void checkNeighbors() {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                Cell currentCell = getCellSocietyGrid().getMyCells()[i][j];
                if (currentCell.getMyCurrentState()!=Cell.EMPTY) {
                    ArrayList<Cell> neighbors = getCellSocietyGrid().getNeighbors(i,j);
                    processNeighbors(currentCell, neighbors);
                }
            }
        }
    }

    abstract void processNeighbors (Cell currentCell, ArrayList<Cell> neighbors);
    abstract void findAndUpdateFutureStates(Cell cell);
    
    public void updateCurrentStates() {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                getCellSocietyGrid().getMyCells()[i][j].setMyCurrentState(getCellSocietyGrid().getMyCells()[i][j].getMyFutureState());
            }
        }
    }
    public void setCellSocietyGrid (GridOfCells cellSocietyGrid) {
        this.cellSocietyGrid = cellSocietyGrid;
    }
    public GridOfCells getCellSocietyGrid () {
        return cellSocietyGrid;
    }
}
