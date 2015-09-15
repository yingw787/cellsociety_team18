import java.util.ArrayList;

public class GridOfCells {
    private Cell[][] myCells;
    private ArrayList<Cell> emptyCells;
    public GridOfCells(Cell[][] cells) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        for (int i=0;i<myCells.length; i++) {
            for (int j=0;j<myCells[0].length; j++) {
                if (myCells[i][j].getMyCurrentState()==Cell.EMPTY) {
                    emptyCells.add(myCells[i][j]);
                }
            }
        }
    }
    public ArrayList<Cell> getAllNeighbors(int row, int column) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int i=row-1; i<=row+1; i++) {
            for (int j=column-1; j<=column+1; j++) {
                if (i>=0 && j>=0 && i<myCells.length && j<myCells[0].length && !(i==row && j==column)) {
                    neighbors.add(myCells[i][j]);
                }
            }
        }
        return neighbors;
    }
    public ArrayList<Cell> getAdjacentToroidalNeighbors(int row, int column) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int i=row-1; i<=row+1; i+=2) {
            i = torusWrap(i);
            neighbors.add(getMyCells()[i][column]);
        }
        for (int j=column-1; j<=column+1; j+=2) {
            j = torusWrap(j);
            neighbors.add(getMyCells()[row][j]);
        }
        return neighbors;
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
    public Cell dequeueRandomGlobalEmpty () {
        int randomIndex = (int)(Math.random()*emptyCells.size());
        Cell result = emptyCells.get(randomIndex);
        emptyCells.remove(result);
        return result;
    }
    public void makeStateEmpty (Cell currentCell) {
        currentCell.setMyFutureState(Cell.EMPTY);
        emptyCells.add(currentCell);
    }
    public int torusWrap (int coordinate) {
        if (coordinate<0) {
            coordinate=getMyCells().length-1;
        }
        else if (coordinate>=getMyCells().length) {
            coordinate=0;
        }
        return coordinate;
    }
    public void swap (Cell currentCell, Cell swapee) {
        if (swapee!=null) {
        Cell temp = currentCell;
        currentCell = swapee;
        swapee = temp;
        }
    }
}
