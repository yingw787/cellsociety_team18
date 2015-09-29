package cell;

import java.util.List;


/**
 * Super class that represents the basic discrete grid location or "cell" that has its own state.
 */
public abstract class Cell {
    public static final int STATE_PARAMETER_INDEX = 0;
    public static final int X_PARAMETER_INDEX = 1;
    public static final int Y_PARAMETER_INDEX = 2;
    public static final int PATCH_1_PARAMETER_INDEX = 3;
    public static final int ANGLE_PARAMETER_INDEX = 4;
    public static final int PATCH_2_PARAMETER_INDEX = 4;
    public static final int NUM_ANT_PARAMETER_INDEX = 5;

    private int myXCoordinate, myYCoordinate, myCurrentState, myFutureState;
    public static final int EMPTY = 0;
    private boolean alreadyMoved;
    private Cell mySwapee;

    /**
     * Instantiates a new cell.
     *
     * @param parameters the parameters
     */
    public Cell (String[] parameters) {
        myCurrentState = Integer.parseInt(parameters[Cell.STATE_PARAMETER_INDEX]);
        myFutureState = Integer.parseInt(parameters[Cell.STATE_PARAMETER_INDEX]);
        alreadyMoved = false;
        setXCoordinate(Integer.parseInt(parameters[Cell.X_PARAMETER_INDEX]) - 1);
        setYCoordinate(Integer.parseInt(parameters[Cell.Y_PARAMETER_INDEX]) - 1);
    }

    /**
     * Gets the my current state.
     *
     * @return the my current state
     */
    public int getCurrentState () {
        return myCurrentState;
    }

    /**
     * Sets the my current state.
     *
     * @param currentState the new my current state
     */
    public void setCurrentState (int currentState) {
        myCurrentState = currentState;
    }

    /**
     * Gets the my future state.
     *
     * @return the my future state
     */
    public int getFutureState () {
        return myFutureState;
    }

    /**
     * Sets the my future state.
     *
     * @param futureState the new my future state
     */
    public void setFutureState (int futureState) {
        myFutureState = futureState;
    }

    /**
     * Gets the swap neighbor.
     *
     * @param neighbors the neighbors
     * @return the swap neighbor
     */
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return null;
    }

    /**
     * Gets the my x coordinate.
     *
     * @return the my x coordinate
     */
    public int getXCoordinate () {
        return myXCoordinate;
    }

    /**
     * Sets the my x coordinate.
     *
     * @param xCoordinate the new my x coordinate
     */
    public void setXCoordinate (int xCoordinate) {
        myXCoordinate = xCoordinate;
    }

    /**
     * Gets the my y coordinate.
     *
     * @return the my y coordinate
     */
    public int getYCoordinate () {
        return myYCoordinate;
    }

    /**
     * Sets the my y coordinate.
     *
     * @param yCoordinate the new my y coordinate
     */
    public void setYCoordinate (int yCoordinate) {
        myYCoordinate = yCoordinate;
    }

    /**
     * Checks if is already moved.
     *
     * @return true, if is already moved
     */
    public boolean isAlreadyMoved () {
        return alreadyMoved;
    }

    /**
     * Sets the already moved.
     *
     * @param alreadyMoved the new already moved
     */
    public void setAlreadyMoved (boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    /**
     * Gets the swapee.
     *
     * @return the swapee
     */
    public Cell getSwapee () {
        return mySwapee;
    }

    /**
     * Sets the swapee.
     *
     * @param swapee the new swapee
     */
    public void setSwapee (Cell swapee) {
        mySwapee = swapee;
    }
}
