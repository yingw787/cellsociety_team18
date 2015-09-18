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
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                Cell currentCell = getCellSocietyGrid().getMyCells()[y][x];
                if (currentCell.getMyCurrentState()!=Cell.EMPTY) {
                    processNeighbors(currentCell, x , y);
                }
            }
        }
    }

    abstract void processNeighbors (Cell currentCell, int x, int y);
    
    public void updateCurrentStates() {
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                getCellSocietyGrid().getMyCells()[y][x].setMyCurrentState(getCellSocietyGrid().getMyCells()[y][x].getMyFutureState());
            }
        }
    }
    public void setCellSocietyGrid (GridOfCells cellSocietyGrid) {
        this.cellSocietyGrid = cellSocietyGrid;
    }
    public GridOfCells getCellSocietyGrid () {
        return cellSocietyGrid;
    }

    //debug helper
    public static void print(GridOfCells g) {
        for (int i=0;i<g.getMyCells().length;i++) {
            for (int j=0;j<g.getMyCells()[0].length;j++) {
                System.out.print(g.getMyCells()[i][j].getMyCurrentState()+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
