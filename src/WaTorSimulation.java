

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;


public class WaTorSimulation extends Simulation {
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy,
            myGainEnergy;

    public WaTorSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
myStepsForFishReproduction = Integer.parseInt(parameters[0]);
setMyStepsForSharkReproduction(Integer.parseInt(parameters[1]));
setMySharkInitialEnergy(Integer.parseInt(parameters[2]));
setMyGainEnergy(Integer.parseInt(parameters[3]));
        for (int i=0;i<cellSocietyGrid.getMyCells().size(); i++) {
            for (int j=0;j<cellSocietyGrid.getMyCells().get(0).size(); j++) {
                Cell cell = cellSocietyGrid.getMyCells().get(i).get(j);
                if (cell.getCurrentState()==SharkCell.SHARK) {
                    SharkCell c = (SharkCell) cell;
                    c.setCurrentEnergy(mySharkInitialEnergy);
                }
            }
        }
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
                if (currentCell.getCurrentState() == fishType) {
                    processNeighbors(currentCell, x, y);
                }
            }
        }
    }

    @Override
    public void processNeighbors (Cell currentCell, int column, int row) {
        WaterCell currentFishSharkCell = (WaterCell) currentCell;
        currentFishSharkCell.setCurrentSteps(currentFishSharkCell.getCurrentSteps() + 1);
        currentFishSharkCell.decrementEnergy();
        //List<Cell> neighbors = getCellSocietyGrid().getNeighbors(column, row);
        List<Pair<Integer,Integer>> intNeighbors =getCellSocietyGrid().processNeighborPoints(getCellSocietyGrid().getSpecificNeighbors(column, row), column, row);
        List<Cell> neighbors=new ArrayList<Cell>();
        for (Pair<Integer,Integer> p: intNeighbors) {
            neighbors.add(getCellSocietyGrid().getMyCells().get(p.getValue()).get(p.getKey()));
        }
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
                WaterCell cell = (WaterCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                if (cell.getCurrentState() == FishCell.FISH && cell.getSwapee() != null &&
                    !cell.getSwapee().isAlreadyMoved() && !cell.isAlreadyMoved()) {
                    swapAndUpdate(x, y, cell, cell.getSwapee());
                    cell.getSwapee().setSwapee(cell);
                    if (cell.getCurrentSteps() >= myStepsForFishReproduction) {
                        cell.setCurrentSteps(0);
                        Cell newFish =
                                new FishCell(cell.getSwapee().getXCoordinate(),
                                             cell.getSwapee().getYCoordinate());
                        getCellSocietyGrid().replace(newFish, cell.getSwapee().getXCoordinate(),
                                                     cell.getSwapee().getYCoordinate());
                    }
                    // cell.setSwapee(null);
                }
            }
        }
    }

    private void updateCurrentSharkStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                WaterCell cell = (WaterCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                if (cell.getCurrentState() == SharkCell.SHARK && cell.getSwapee() != null &&
                    !cell.isAlreadyMoved()) { // TODO: if statement is ok?
                    if (cell.getSwapee().isAlreadyMoved()) {
                        if (cell.getSwapee().getSwapee() != null) {
                            swapAndUpdate(x, y, cell, cell.getSwapee().getSwapee());
                        }
                    }
                    else {
                        swapAndUpdate(x, y, cell, cell.getSwapee());
                    }
                    if (cell.getCurrentSteps() >= myStepsForSharkReproduction) {
                        cell.setCurrentSteps(0);
                        Cell newShark =
                                new SharkCell(new String[]{new Integer(x+1).toString(), new Integer(y+1).toString()});
                        getCellSocietyGrid().replace(newShark, x, y);
                    }
                    // cell.setSwapee(null);
                }
            }
        }
    }

    public void swapAndUpdate (int x, int y, WaterCell cell, Cell swapee) {
        System.out.println(swapee.getYCoordinate()+" "+swapee.getXCoordinate());
        if (getCellSocietyGrid().getMyCells().get(swapee.getYCoordinate()).get(swapee.getXCoordinate())
                .getCurrentState() != SharkCell.SHARK) {
            getCellSocietyGrid().swap(x, y, swapee.getXCoordinate(), swapee.getYCoordinate()); // shark
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
            if (getCellSocietyGrid().getMyCells().get(y).get(x).getCurrentState() == FishCell.FISH) {
                cell.setCurrentEnergy(cell.getCurrentEnergy() + myGainEnergy);
            }
            else if (cell.getCurrentEnergy() <= 0) {
                makeEmpty(cell);
            }
            makeEmpty(x, y);
        }
    }

    public void makeEmpty (Cell cell) {
        cell.setCurrentState(Cell.EMPTY);
        cell.setFutureState(Cell.EMPTY);
    }

    public void makeEmpty (int x, int y) {
        getCellSocietyGrid().getMyCells().get(y).get(x).setCurrentState(Cell.EMPTY);
        getCellSocietyGrid().getMyCells().get(y).get(x).setFutureState(Cell.EMPTY);
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

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }
}
