import java.util.ArrayList;

public abstract class Cell {
    private int myXCoordinate, myYCoordinate, myCurrentState, myFutureState;
    public static final int EMPTY = 0;
    
    public Cell(int state) {
        myCurrentState = state;
        myFutureState = state;
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

    public Cell getSwapNeighbor (ArrayList<Cell> neighbors) {
        return null;
        // TODO Auto-generated method stub
        
    }
}