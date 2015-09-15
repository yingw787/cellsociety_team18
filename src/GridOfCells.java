import java.util.ArrayList;

public class GridOfCells {
    private Cell[][] myCells;
    private ArrayList<Cell> emptyCells;
    public GridOfCells(Cell[][] cells) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        for (int y=0;y<myCells.length; y++) {
            for (int x=0;x<myCells[0].length; x++) {
                if (myCells[y][x].getMyCurrentState()==Cell.EMPTY) {
                    emptyCells.add(myCells[y][x]);
                }
            }
        }
    }
    public ArrayList<Cell> getAllNeighbors(int column, int row) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int y=row-1; y<=row+1; y++) {
            for (int x=column-1; x<=column+1; x++) {
                if (y>=0 && x>=0 && y<myCells.length && x<myCells[0].length && !(y==row && x==column)) {
                    neighbors.add(myCells[y][x]);
                }
            }
        }
        return neighbors;
    }
    public ArrayList<Cell> getAdjacentToroidalNeighbors(int column, int row) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int y=row-1; y<=row+1; y+=2) {
            y = torusWrap(y);
            neighbors.add(getMyCells()[y][column]);
        }
        for (int x=column-1; x<=column+1; x+=2) {
            x = torusWrap(x);
            neighbors.add(getMyCells()[row][x]);
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
    public void swap (int currentX, int currentY, int swapeeX, int swapeeY) {
            Cell temp = myCells[currentY][currentX];
            myCells[currentY][currentX] = myCells[swapeeX][swapeeY];
            myCells[currentY][currentX].setMyXCoordinate(swapeeX);
            myCells[currentY][currentX].setMyYCoordinate(swapeeY);
            myCells[swapeeY][swapeeX] = temp;
            myCells[swapeeY][swapeeX].setMyXCoordinate(currentX);
            myCells[swapeeY][swapeeX].setMyYCoordinate(currentY);
    }
}
