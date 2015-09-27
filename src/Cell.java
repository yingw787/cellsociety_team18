import java.util.List;


public abstract class Cell {
    public final int STATE_PARAMETER_INDEX = 0;
    public final int X_PARAMETER_INDEX = 1;
    public final int Y_PARAMETER_INDEX = 2;
    public final int ANGLE_PARAMETER_INDEX = 3;
    
    
    private int myXCoordinate, myYCoordinate, myCurrentState, myFutureState;
    public static final int EMPTY = 0;
    private boolean alreadyMoved;
    private Cell mySwapee;

    public Cell (String[] parameters) {
        myCurrentState = Integer.parseInt(parameters[this.STATE_PARAMETER_INDEX]);
        myFutureState = Integer.parseInt(parameters[this.STATE_PARAMETER_INDEX]);
        alreadyMoved = false;
        setMyXCoordinate(Integer.parseInt(parameters[this.X_PARAMETER_INDEX])-1);
        setMyYCoordinate(Integer.parseInt(parameters[this.Y_PARAMETER_INDEX])-1);
    }

    public int getMyCurrentState () {
        return myCurrentState;
    }

    public void setMyCurrentState (int myCurrentState) {
        this.myCurrentState = myCurrentState;
    }

    public int getMyFutureState () {
        return myFutureState;
    }

    public void setMyFutureState (int myFutureState) {
        this.myFutureState = myFutureState;
    }

    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return null;
        // TODO Auto-generated method stub

    }

    public int getMyXCoordinate () {
        return myXCoordinate;
    }

    public void setMyXCoordinate (int myXCoordinate) {
        this.myXCoordinate = myXCoordinate;
    }

    public int getMyYCoordinate () {
        return myYCoordinate;
    }

    public void setMyYCoordinate (int myYCoordinate) {
        this.myYCoordinate = myYCoordinate;
    }

    public boolean isAlreadyMoved () {
        return alreadyMoved;
    }

    public void setAlreadyMoved (boolean alreadyMoved) {
        this.alreadyMoved = alreadyMoved;
    }

    public Cell getSwapee () {
        return mySwapee;
    }

    public void setSwapee (Cell swapee) {
        mySwapee = swapee;
    }
}
