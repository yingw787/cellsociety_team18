
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


public class GridOfCells {
    private Cell[][] myCells;
    private ArrayList<Cell> emptyCells;
    private HashMap<Integer, Color> myColorMap;

    public GridOfCells (Cell[][] cells, HashMap<Integer, Color> colorMap) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        myColorMap = colorMap;
        for (Cell[] myCell : myCells) {
            for (int x = 0; x < myCells[0].length; x++) {
                if (myCell[x].getMyCurrentState() == Cell.EMPTY) {
                    emptyCells.add(myCell[x]);
                }
            }
        }
    }

    public ArrayList<Cell> getNeighbors (int column, int row) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        for (int y = row - 1; y <= row + 1; y += 2) {
            if (y >= 0 && y < myCells.length) {
                neighbors.add(getMyCells()[y][column]);
            }
        }
        for (int x = column - 1; x <= column + 1; x += 2) {
            if (x >= 0 && x < myCells[0].length) {
                neighbors.add(getMyCells()[row][x]);
            }
        }
        return neighbors;
    }

    public Color getCellColor (int x, int y) {
        Cell cell = myCells[y][x];
        return myColorMap.get(cell.getMyCurrentState());
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
        int randomIndex = (int) (Math.random() * emptyCells.size());
        Cell result = emptyCells.get(randomIndex);
        emptyCells.remove(result);
        return result;
    }

    public void makeStateEmpty (Cell currentCell) {
        currentCell.setMyFutureState(Cell.EMPTY);
        emptyCells.add(currentCell);
    }

    public int torusWrapX (int coordinate) {
        if (coordinate < 0) {
            coordinate = getMyCells()[0].length - 1;
        }
        else if (coordinate >= getMyCells()[0].length) {
            coordinate = 0;
        }
        return coordinate;
    }

    public int torusWrapY (int coordinate) {
        if (coordinate < 0) {
            coordinate = getMyCells().length - 1;
        }
        else if (coordinate >= getMyCells().length) {
            coordinate = 0;
        }
        return coordinate;
    }

    public void swap (int currentX, int currentY, int swapeeX, int swapeeY) {
        Cell temp = myCells[currentY][currentX];
        myCells[currentY][currentX] = myCells[swapeeY][swapeeX];
        myCells[currentY][currentX].setMyXCoordinate(currentX);
        myCells[currentY][currentX].setMyYCoordinate(currentY);
        myCells[swapeeY][swapeeX] = temp;
        myCells[swapeeY][swapeeX].setMyXCoordinate(swapeeX);
        myCells[swapeeY][swapeeX].setMyYCoordinate(swapeeY);
    }

    public void replace (Cell updated, int updateX, int updateY) {
        myCells[updateY][updateX] = updated;
    }
}
