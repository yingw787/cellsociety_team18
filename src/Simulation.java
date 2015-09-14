

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
        updateFutureState();
    }
    
    public abstract void checkNeighbors();
    
    public void moveCells() {
        
    }
    public void updateFutureState() {
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
