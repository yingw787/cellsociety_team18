package simulation;

import java.util.ArrayList;
import java.util.List;
import cell.Cell;
import cell.FishCell;
import cell.SharkCell;
import cell.WaterCell;
import grid.GridOfCells;
import javafx.util.Pair;


/**
 * Hold the rules and variables for the WaTor World simulation.
 */
public class WaTorSimulation extends Simulation {
    private int myStepsForFishReproduction, myStepsForSharkReproduction, mySharkInitialEnergy,
            myGainEnergy;

    /**
     * Instantiates a new WaTor simulation.
     *
     * @param cellSocietyGrid the cell society grid
     * @param parameters the parameters
     */
    public WaTorSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        myStepsForFishReproduction = Integer.parseInt(parameters[0]);
        setStepsForSharkReproduction(Integer.parseInt(parameters[1]));
        setSharkInitialEnergy(Integer.parseInt(parameters[2]));
        setGainEnergy(Integer.parseInt(parameters[3]));
        for (int i = 0; i < cellSocietyGrid.getCells().size(); i++) {
            for (int j = 0; j < cellSocietyGrid.getCells().get(0).size(); j++) {
                Cell cell = cellSocietyGrid.getCells().get(i).get(j);
                if (cell.getCurrentState() == SharkCell.SHARK) {
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

    /*
     * (non-Javadoc)
     *
     * @see Simulation#checkNeighbors()
     */
    @Override
    public void checkNeighbors () {
        resetStepState();
        checkNeighbors(FishCell.FISH); // first move all the fish
    }

    /**
     * Reset step state.
     */
    private void resetStepState () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                getCellSocietyGrid().getCells().get(y).get(x).setSwapee(null);
                getCellSocietyGrid().getCells().get(y).get(x).setAlreadyMoved(false);
            }
        }
    }

    /**
     * Check neighbors.
     *
     * @param fishType the fish type
     */
    public void checkNeighbors (int fishType) {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                Cell currentCell = getCellSocietyGrid().getCells().get(y).get(x);
                if (currentCell.getCurrentState() == fishType) { // this if statement is needed
 // since there might not be a
 // simple
 // way to move all fishes before
 // sharks without using reflection.
                    processNeighbors(currentCell, x, y);
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see Simulation#processNeighbors(Cell, int, int)
     */
    @Override
    public void processNeighbors (Cell currentCell, int column, int row) {
        WaterCell currentFishSharkCell = (WaterCell) currentCell;
        currentFishSharkCell.setCurrentSteps(currentFishSharkCell.getCurrentSteps() + 1);
        currentFishSharkCell.decrementEnergy();
        List<Pair<Integer, Integer>> intNeighbors =
                getCellSocietyGrid().processNeighborPoints(getCellSocietyGrid()
                        .getSpecificNeighbors(column, row), column, row);
        List<Cell> neighbors = new ArrayList<Cell>();
        for (Pair<Integer, Integer> p : intNeighbors) {
            neighbors.add(getCellSocietyGrid().getCells().get(p.getValue()).get(p.getKey()));
            System.out.print(p.getValue() + "-" + p.getKey() + " ");
        }
        System.out.println(" ");
        currentFishSharkCell.setSwapee(currentCell.getSwapNeighbor(neighbors));
    }

    /**
     * Check shark neighbors.
     */
    private void checkSharkNeighbors () {
        checkNeighbors(SharkCell.SHARK); // then move all the sharks
    }

    /*
     * (non-Javadoc)
     *
     * @see Simulation#secondPass()
     */
    @Override
    public void secondPass () {
        updateCurrentStates();
        updateCurrentSharkStates();
    }

    /*
     * (non-Javadoc)
     *
     * @see Simulation#updateCurrentStates()
     */
    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                WaterCell cell = (WaterCell) getCellSocietyGrid().getCells().get(y).get(x);
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
                }
            }
        }
    }

    /**
     * Update current shark states.
     */
    private void updateCurrentSharkStates () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                WaterCell cell = (WaterCell) getCellSocietyGrid().getCells().get(y).get(x);
                if (cell.getCurrentState() == SharkCell.SHARK && cell.getSwapee() != null &&
                    !cell.isAlreadyMoved()) {
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
                                new SharkCell(new String[] { new Integer(x + 1).toString(),
                                                             new Integer(y + 1).toString() });
                        getCellSocietyGrid().replace(newShark, x, y);
                    }
                }
            }
        }
    }

    /**
     * Swap and update.
     *
     * @param x the x
     * @param y the y
     * @param cell the cell
     * @param swapee the swapee
     */
    public void swapAndUpdate (int x, int y, WaterCell cell, Cell swapee) {
        System.out.println(swapee.getYCoordinate() + " " + swapee.getXCoordinate());
        if (getCellSocietyGrid().getCells().get(swapee.getYCoordinate())
                .get(swapee.getXCoordinate())
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
            // fish
            swapee.setAlreadyMoved(true);
            cell.setAlreadyMoved(true);
            if (getCellSocietyGrid().getCells().get(y).get(x).getCurrentState() == FishCell.FISH) {
                cell.setCurrentEnergy(cell.getCurrentEnergy() + myGainEnergy);
            }
            else if (cell.getCurrentEnergy() <= 0) {
                makeEmpty(cell);
            }
            makeEmpty(x, y);
        }
    }

    /**
     * Make cell empty.
     *
     * @param cell the cell
     */
    public void makeEmpty (Cell cell) {
        cell.setCurrentState(Cell.EMPTY);
        cell.setFutureState(Cell.EMPTY);
    }

    /**
     * Make location empty.
     *
     * @param x the x
     * @param y the y
     */
    public void makeEmpty (int x, int y) {
        getCellSocietyGrid().getCells().get(y).get(x).setCurrentState(Cell.EMPTY);
        getCellSocietyGrid().getCells().get(y).get(x).setFutureState(Cell.EMPTY);
    }

    /**
     * Gets the steps for fish reproduction.
     *
     * @return the steps for fish reproduction
     */
    public int getStepsForFishReproduction () {
        return myStepsForFishReproduction;
    }

    /**
     * Sets the steps for fish reproduction.
     *
     * @param stepsForFishReproduction the new steps for fish reproduction
     */
    public void setStepsForFishReproduction (int stepsForFishReproduction) {
        myStepsForFishReproduction = stepsForFishReproduction;
    }

    /**
     * Gets the steps for shark reproduction.
     *
     * @return the steps for shark reproduction
     */
    public int getStepsForSharkReproduction () {
        return myStepsForSharkReproduction;
    }

    /**
     * Sets the steps for shark reproduction.
     *
     * @param stepsForSharkReproduction the new steps for shark reproduction
     */
    public void setStepsForSharkReproduction (int stepsForSharkReproduction) {
        myStepsForSharkReproduction = stepsForSharkReproduction;
    }

    /**
     * Gets the shark initial energy.
     *
     * @return the shark initial energy
     */
    public int getSharkInitialEnergy () {
        return mySharkInitialEnergy;
    }

    /**
     * Sets the shark initial energy.
     *
     * @param sharkInitialEnergy the new shark initial energy
     */
    public void setSharkInitialEnergy (int sharkInitialEnergy) {
        mySharkInitialEnergy = sharkInitialEnergy;
    }

    /**
     * Gets the gain energy.
     *
     * @return the gain energy
     */
    public int getGainEnergy () {
        return myGainEnergy;
    }

    /**
     * Sets the gain energy.
     *
     * @param gainEnergy the new gain energy
     */
    public void setGainEnergy (int gainEnergy) {
        myGainEnergy = gainEnergy;
    }
}
