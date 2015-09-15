import java.util.ArrayList;

public class WaTorSimulation extends Simulation{
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy;

    public WaTorSimulation(int stepsForFishReproduction, int stepsForSharkReproduction, int sharkInitialEnergy) {
        myStepsForFishReproduction = stepsForFishReproduction;
        myStepsForSharkReproduction = stepsForSharkReproduction;
        mySharkInitialEnergy = sharkInitialEnergy;
    }
    @Override
    public void checkNeighbors() {
        checkNeighbors(FishCell.FISH); //first move all the fish
        checkNeighbors(SharkCell.SHARK); //then move all the sharks
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
        ArrayList<Cell> neighbors = getCellSocietyGrid().getAdjacentToroidalNeighbors(i,j);
        Cell swapee = currentCell.getSwapNeighbor(neighbors);
        getCellSocietyGrid().swap(currentCell, swapee);
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
