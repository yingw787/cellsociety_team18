import java.util.ArrayList;

public class WaTorSimulation extends Simulation{
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy;

    public WaTorSimulation(int stepsForFishReproduction, int stepsForSharkReproduction, int sharkInitialEnergy) {
        myStepsForFishReproduction = stepsForFishReproduction;
        myStepsForSharkReproduction = stepsForSharkReproduction;
        mySharkInitialEnergy = sharkInitialEnergy;
    }
    @Override
    void processNeighbors (Cell currentCell, int i, int j) {
        ArrayList<Cell> neighbors = getCellSocietyGrid().getAdjacentToroidalNeighbors(i,j);
    }

    @Override
    void findAndUpdateFutureStates (Cell cell) {
        
    }

}
