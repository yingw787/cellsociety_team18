import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GridOfCells {
    private Cell[][] myCells;
    private Queue<Cell> emptyCells;
    public GridOfCells(Cell[][] cells) {
        myCells = cells;
        emptyCells = new LinkedList<Cell>();
        for (int i=0;i<myCells.length; i++) {
            for (int j=0;j<myCells[0].length; j++) {
                if (myCells[i][j].getMyCurrentState()==Cell.EMPTY) {
                    emptyCells.add(myCells[i][j]);
                }
            }
        }
    }
    public ArrayList<Cell> getNeighbors(int row, int column) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int i=row-1; i<=row+1; i++) {
            for (int j=column-1; j<=column+1; j++) {
                if (i>=0 && j>=0 && i<myCells.length && j<myCells[0].length) {
                    neighbors.add(myCells[i][j]);
                }
            }
        }
        return neighbors;
    }
    
    public static void main(String[] args) {
        Cell[] cells = new Cell[5];
    }
    public Cell[][] getMyCells () {
        return myCells;
    }
    public void setMyCells (Cell[][] myCells) {
        this.myCells = myCells;
    }
    public void changeEmptyState (Cell emptyCell, int newState) {
        emptyCell.setMyFutureState(newState);
    }
    public Cell dequeueNextGlobalEmpty () {
        return emptyCells.poll();
    }
    public void makeStateEmpty (Cell currentCell) {
        currentCell.setMyFutureState(Cell.EMPTY);
        emptyCells.add(currentCell);
    }
}
