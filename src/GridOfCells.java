
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;


public class GridOfCells {
    private Cell[][] myCells;
    private ArrayList<Cell> emptyCells;
    private HashMap<Integer, Color> myColorMap;
    private NeighborProcessor myEdgeType, myDiagonalNeighbor;

    public GridOfCells (Cell[][] cells, HashMap<Integer, Color> colorMap, NeighborProcessor edgeType, NeighborProcessor diagonalNeighbor) {
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
        myEdgeType=edgeType;
        myDiagonalNeighbor=diagonalNeighbor;
    }

    public ArrayList<Cell> getNeighbors (int column, int row) {
        ArrayList<Pair<Integer,Integer>> neighborPoints = getSpecificNeighbors(column,row);
        neighborPoints=processNeighborPoints(neighborPoints,column,row);
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        for (Pair<Integer,Integer> p: neighborPoints) {
            neighbors.add(getMyCells()[p.getValue()][p.getKey()]);
        }
        return neighbors;
//        for (int y = row - 1; y <= row + 1; y += 2) {
//            if (y >= 0 && y < myCells.length) {
//                neighbors.add(new Pair<Integer,Integer>(column,y));
//            }
//        }
//        for (int x = column - 1; x <= column + 1; x += 2) {
//            if (x >= 0 && x < myCells[0].length) {
//                neighbors.add(new Pair<Integer,Integer>(x,row));
//            }
//        }
//        return neighbors;
    }

    public ArrayList<Pair<Integer,Integer>> getSpecificNeighbors(int column, int row) {
        ArrayList<Pair<Integer,Integer>> neighborPoints = new ArrayList<Pair<Integer,Integer>>();
        for (int y = row - 1; y <= row + 1; y++) {
            for (int x = column - 1; x <= column + 1; x++) {
                if (/*y >= 0 && x >= 0 && y < getMyCells().length && x < getMyCells()[0].length &&*/
                    !(y == row && x == column)) {
                    neighborPoints.add(new Pair<Integer,Integer>(x,y));
                }
            }
        }
        return neighborPoints;
    }
    
    public ArrayList<Pair<Integer, Integer>> processNeighborPoints(ArrayList<Pair<Integer,Integer>> neighborPoints, int column, int row) {
        neighborPoints=myEdgeType.process(column, row, neighborPoints,getMyCells()[0].length,getMyCells().length);
        neighborPoints=myDiagonalNeighbor.process(column, row, neighborPoints,getMyCells()[0].length,getMyCells().length);
        return neighborPoints;
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
