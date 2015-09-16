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
     * If two fish choose to move to the same location, whichever one gets there first will move there and the other will not move. A shark
     * can only see its current neighbors and so will basically follow a fish if it can. If a shark decides to move to an empty space which is
     * then immediately filled in by a fish, the shark will move to the same space and eat the fish.
     */
    @Override
    public void firstPass() {
        checkNeighbors();
        checkSharkNeighbors();
    }
    @Override
    public void secondPass() {
        updateCurrentStates();
        updateCurrentSharkStates();
    }
    private void updateCurrentSharkStates () {
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                FishSharkCell cell = (FishSharkCell)getCellSocietyGrid().getMyCells()[y][x];
                if (cell.getMyCurrentState()==SharkCell.SHARK && cell.getSwapee()!=null && !cell.isAlreadyMoved()) { //TODO: if statement is ok?
                    System.out.println("before swap"+x+y);
                    System.out.println("me: "+cell);
                    System.out.println("target: "+cell.getSwapee().getSwapee()+cell.getSwapee().getMyXCoordinate()+cell.getSwapee().getMyYCoordinate());
                    if (cell.getSwapee().isAlreadyMoved()) {
                        swapAndUpdateChainTwo(x, y, cell);
                        makeEmpty(cell.getSwapee().getSwapee());
                    }
                    else {
                        System.out.println("else");
                        swapAndUpdate(x, y, cell);
                        makeEmpty(cell.getSwapee());
                    }
                    //cell.setSwapee(null);
                }
            }
        }
    }
    public void swapAndUpdateChainTwo (int x, int y, FishSharkCell cell) {
        getCellSocietyGrid().swap(x, y, cell.getSwapee().getSwapee().getMyXCoordinate(), cell.getSwapee().getSwapee().getMyYCoordinate()); //shark will always swap with an empty or a fish?
        cell.getSwapee().getSwapee().setAlreadyMoved(true);
        cell.setAlreadyMoved(true);
    }
    public void makeEmpty (Cell cell) {
        cell.setMyCurrentState(Cell.EMPTY);
        cell.setMyFutureState(Cell.EMPTY);
    }
    public void swapAndUpdate (int x, int y, FishSharkCell cell) {
        getCellSocietyGrid().swap(x, y, cell.getSwapee().getMyXCoordinate(), cell.getSwapee().getMyYCoordinate()); //shark will always swap with an empty or a fish?
        cell.getSwapee().setAlreadyMoved(true);
        cell.setAlreadyMoved(true);
    }
    private void checkSharkNeighbors () {
        checkNeighbors(SharkCell.SHARK); //then move all the sharks
    }
    @Override
    public void checkNeighbors() {
        resetStepState();
        checkNeighbors(FishCell.FISH); //first move all the fish
    }
    private void resetStepState () {
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                getCellSocietyGrid().getMyCells()[y][x].setSwapee(null);
                getCellSocietyGrid().getMyCells()[y][x].setAlreadyMoved(false);
            }
        }
    }
    public void checkNeighbors (int fishType) {
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                Cell currentCell = getCellSocietyGrid().getMyCells()[y][x];
                if (currentCell.getMyCurrentState()==fishType) {
                    processNeighbors(currentCell, x , y);
                }
            }
        }
    }

    @Override
    public void processNeighbors (Cell currentCell, int column, int row) {
        FishSharkCell currentFishSharkCell = (FishSharkCell)currentCell;
        ArrayList<Cell> neighbors = getCellSocietyGrid().getAdjacentToroidalNeighbors(column,row);
        currentFishSharkCell.setSwapee((FishSharkCell)currentCell.getSwapNeighbor(neighbors));
    }
    @Override
    public void updateCurrentStates() {
        for (int y=0; y<getCellSocietyGrid().getMyCells().length; y++){
            for (int x=0; x<getCellSocietyGrid().getMyCells()[0].length; x++) {
                FishSharkCell cell = (FishSharkCell)getCellSocietyGrid().getMyCells()[y][x];
                if (cell.getMyCurrentState()==FishCell.FISH && cell.getSwapee()!=null && !cell.getSwapee().isAlreadyMoved() && !cell.isAlreadyMoved()) {
                    System.out.println("before swap"+x+y);
                    System.out.println("me: "+cell);
                    System.out.println("target: "+cell.getSwapee());//)+cell.getSwapee().getMyYCoordinate());
                    swapAndUpdate(x, y, cell);
                    cell.getSwapee().setSwapee(cell);
                    //cell.setSwapee(null);
                }
            }
        }
    }

    @Override
    public void findAndUpdateFutureStates (Cell cell) {

    }

}
