import java.util.ArrayList;

public class WaTorSimulation extends Simulation{
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy;

    public WaTorSimulation(int stepsForFishReproduction, int stepsForSharkReproduction, int sharkInitialEnergy) {
        myStepsForFishReproduction = stepsForFishReproduction;
        myStepsForSharkReproduction = stepsForSharkReproduction;
        mySharkInitialEnergy = sharkInitialEnergy;
    }
    /**
     * This step method follows the project specification: there is a first pass that applies movement rules and a second pass that actually
     * updates the state. For example, if a fish is can't move anywhere but one of the adjacent fish moves away, the fish still will not move.
     * If two fish choose to move to the same location, whichever one gets there first will move there and the other will not move.
     */
    @Override
    public void step() {
        firstPass(); 
        secondPass();
        checkSharkNeighbors();
        updateCurrentSharkStates();
    }
    private void updateCurrentSharkStates () {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                FishSharkCell cell = (FishSharkCell)getCellSocietyGrid().getMyCells()[i][j];
                if (cell.getSwapee()!=null) {
                    getCellSocietyGrid().swap(cell, cell.getSwapee()); //shark will always swap with an empty or a fish?
                    cell.getSwapee().setMyCurrentState(Cell.EMPTY);
                    cell.getSwapee().setMyFutureState(Cell.EMPTY);
                    cell.setSwapee(null);
                }
            }
        }
        // TODO Auto-generated method stub
        
    }
    private void checkSharkNeighbors () {
        checkNeighbors(SharkCell.SHARK); //then move all the sharks
    }
    @Override
    public void checkNeighbors() {
        checkNeighbors(FishCell.FISH); //first move all the fish
    }
    public void checkNeighbors (int fishType) {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                Cell currentCell = getCellSocietyGrid().getMyCells()[i][j];
                if (currentCell.getMyCurrentState()==fishType) {
                    processNeighbors(currentCell, i , j);
                }
            }
        }
    }
    
    @Override
    public void processNeighbors (Cell currentCell, int i, int j) {
        FishSharkCell currentFishSharkCell = (FishSharkCell)currentCell;
        ArrayList<Cell> neighbors = getCellSocietyGrid().getAdjacentToroidalNeighbors(i,j);
        currentFishSharkCell.setSwapee((FishSharkCell)currentCell.getSwapNeighbor(neighbors));
    }
    @Override
    public void updateCurrentStates() {
        for (int i=0; i<getCellSocietyGrid().getMyCells().length; i++){
            for (int j=0; j<getCellSocietyGrid().getMyCells()[0].length; j++) {
                FishSharkCell cell = (FishSharkCell)getCellSocietyGrid().getMyCells()[i][j];
                if (cell.getSwapee()!=null) {
                    getCellSocietyGrid().swap(cell, cell.getSwapee()); //fish will always swap with an empty?
                    cell.setSwapee(null);
                }
            }
        }
    }
    
    @Override
    public void findAndUpdateFutureStates (Cell cell) {
        
    }
    

    public void moveFishes() {
        for (int i=0;i<getCellSocietyGrid().getMyCells().length; i++) {
            for (int j=0;j<getCellSocietyGrid().getMyCells()[0].length;j++) {
                FishSharkCell cell = (FishSharkCell)getCellSocietyGrid().getMyCells()[i][j];
                
            }
        }
    }
    
}
