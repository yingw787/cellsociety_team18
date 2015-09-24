
import java.util.ArrayList;
import java.util.List;


public class WaTorSimulation extends Simulation {
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy,
            myGainEnergy;

    public WaTorSimulation (GridOfCells cellSocietyGrid,
                            int stepsForFishReproduction,
                            int stepsForSharkReproduction,
                            int sharkInitialEnergy,
                            int gainEnergy) {
        super(cellSocietyGrid);
        myStepsForFishReproduction = stepsForFishReproduction;
        setMyStepsForSharkReproduction(stepsForSharkReproduction);
        setMySharkInitialEnergy(sharkInitialEnergy);
        setMyGainEnergy(gainEnergy);
    }

    /**
     * This step method follows the project specification: there is a first pass that applies
     * movement rules and a second pass that actually
     * updates the state. For example, if a fish is can't move anywhere but one of the adjacent fish
     * moves away, the fish still will not move.
     * If two fish choose to move to the same location, whichever one gets there first will move
     * there and the other will not move. A shark
     * can only see its current neighbors and so will basically follow a fish if it can. If a shark
     * decides to move to an empty space which is
     * then immediately filled in by a fish, the shark will move to the same space and eat the fish.
     */
    @Override
    public void firstPass () {
        checkNeighbors();
        checkSharkNeighbors();
    }

    @Override
    public void checkNeighbors () {
        resetStepState();
        checkNeighbors(FishCell.FISH); // first move all the fish
    }

    private void resetStepState () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                getCellSocietyGrid().getMyCells().get(y).get(x).setSwapee(null);
                getCellSocietyGrid().getMyCells().get(y).get(x).setAlreadyMoved(false);
            }
        }
    }

    public void checkNeighbors (int fishType) {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                Cell currentCell = getCellSocietyGrid().getMyCells().get(y).get(x);
                if (currentCell.getMyCurrentState() == fishType) {
                    processNeighbors(currentCell, x, y);
                }
            }
        }
    }

    @Override
    public void processNeighbors (Cell currentCell, int column, int row) {
        FishSharkCell currentFishSharkCell = (FishSharkCell) currentCell;
        currentFishSharkCell.setMyCurrentSteps(currentFishSharkCell.getMyCurrentSteps() + 1);
        currentFishSharkCell.decrementEnergy();
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(column, row);
        currentFishSharkCell.setSwapee(currentCell.getSwapNeighbor(neighbors));
    }

    private void checkSharkNeighbors () {
        checkNeighbors(SharkCell.SHARK); // then move all the sharks
    }

    @Override
    public void secondPass () {
        updateCurrentStates();
        updateCurrentSharkStates();
    }

    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                FishSharkCell cell = (FishSharkCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                if (cell.getMyCurrentState() == FishCell.FISH && cell.getSwapee() != null &&
                    !cell.getSwapee().isAlreadyMoved() && !cell.isAlreadyMoved()) {
                    swapAndUpdate(x, y, cell, cell.getSwapee());
                    cell.getSwapee().setSwapee(cell);
                    if (cell.getMyCurrentSteps() >= cell.getMyReproductionSteps()) {
                        cell.setMyCurrentSteps(0);
                        Cell newFish =
                                new FishCell(cell.getSwapee().getMyXCoordinate(),
                                             cell.getSwapee().getMyYCoordinate(),
                                             myStepsForFishReproduction);
                        getCellSocietyGrid().replace(newFish, cell.getSwapee().getMyXCoordinate(),
                                                     cell.getSwapee().getMyYCoordinate());
                    }
                    // cell.setSwapee(null);
                }
            }
        }
    }

    private void updateCurrentSharkStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                FishSharkCell cell = (FishSharkCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                if (cell.getMyCurrentState() == SharkCell.SHARK && cell.getSwapee() != null &&
                    !cell.isAlreadyMoved()) { // TODO: if statement is ok?
                    if (cell.getSwapee().isAlreadyMoved()) {
                        if (cell.getSwapee().getSwapee() != null) {
                            swapAndUpdate(x, y, cell, cell.getSwapee().getSwapee());
                        }
                    }
                    else {
                        swapAndUpdate(x, y, cell, cell.getSwapee());
                    }
                    if (cell.getMyCurrentSteps() >= cell.getMyReproductionSteps()) {
                        cell.setMyCurrentSteps(0);
                        Cell newShark =
                                new SharkCell(x, y, myStepsForSharkReproduction,
                                              mySharkInitialEnergy, myGainEnergy);
                        getCellSocietyGrid().replace(newShark, x, y);
                    }
                    // cell.setSwapee(null);
                }
            }
        }
    }

    public void swapAndUpdate (int x, int y, FishSharkCell cell, Cell swapee) {
        if (getCellSocietyGrid().getMyCells().get(swapee.getMyYCoordinate()).get(swapee.getMyXCoordinate())
                .getMyCurrentState() != SharkCell.SHARK) {
            getCellSocietyGrid().swap(x, y, swapee.getMyXCoordinate(), swapee.getMyYCoordinate()); // shark
                                                                                                   // will
                                                                                                   // always
                                                                                                   // swap
                                                                                                   // with
                                                                                                   // an
                                                                                                   // empty
                                                                                                   // or
                                                                                                   // a
                                                                                                   // fish?
            swapee.setAlreadyMoved(true);
            cell.setAlreadyMoved(true);
            if (getCellSocietyGrid().getMyCells().get(y).get(x).getMyCurrentState() == FishCell.FISH) {
                cell.setMyCurrentEnergy(cell.getMyCurrentEnergy() + myGainEnergy);
            }
            else if (cell.getMyCurrentEnergy() <= 0) {
                makeEmpty(cell);
            }
            makeEmpty(x, y);
        }
    }

    public void makeEmpty (Cell cell) {
        cell.setMyCurrentState(Cell.EMPTY);
        cell.setMyFutureState(Cell.EMPTY);
    }

    public void makeEmpty (int x, int y) {
        getCellSocietyGrid().getMyCells().get(y).get(x).setMyCurrentState(Cell.EMPTY);
        getCellSocietyGrid().getMyCells().get(y).get(x).setMyFutureState(Cell.EMPTY);
    }

    public int getMyStepsForFishReproduction () {
        return myStepsForFishReproduction;
    }

    public void setMyStepsForFishReproduction (int myStepsForFishReproduction) {
        this.myStepsForFishReproduction = myStepsForFishReproduction;
    }

    public int getMyStepsForSharkReproduction () {
        return myStepsForSharkReproduction;
    }

    public void setMyStepsForSharkReproduction (int myStepsForSharkReproduction) {
        this.myStepsForSharkReproduction = myStepsForSharkReproduction;
    }

    public int getMySharkInitialEnergy () {
        return mySharkInitialEnergy;
    }

    public void setMySharkInitialEnergy (int mySharkInitialEnergy) {
        this.mySharkInitialEnergy = mySharkInitialEnergy;
    }

    public int getMyGainEnergy () {
        return myGainEnergy;
    }

    public void setMyGainEnergy (int myGainEnergy) {
        this.myGainEnergy = myGainEnergy;
    }
}
