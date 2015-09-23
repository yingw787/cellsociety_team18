
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;


public class GridOfCells {
    private ArrayList<ArrayList<Cell>> myCells;
    private ArrayList<Cell> emptyCells;
    private HashMap<Integer, Color> myColorMap;
    private NeighborProcessor myEdgeType, myDiagonalNeighbor;

    public GridOfCells (ArrayList<ArrayList<Cell>> cells, HashMap<Integer, Color> colorMap, NeighborProcessor edgeType, NeighborProcessor diagonalNeighbor) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        myColorMap = colorMap;
        for (ArrayList<Cell> myCellRow : myCells) {
            for (int x = 0; x < myCellRow.size(); x++) {
                if (myCellRow.get(x).getMyCurrentState() == Cell.EMPTY) {
                    emptyCells.add(myCellRow.get(x));
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
            neighbors.add(getMyCells().get(p.getValue()).get(p.getKey()));
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
        neighborPoints=myEdgeType.process(column, row, neighborPoints,getMyCells().get(0).size(),getMyCells().size());
        neighborPoints=myDiagonalNeighbor.process(column, row, neighborPoints,getMyCells().get(0).size(),getMyCells().size());
        return neighborPoints;
    }
    
    public Color getCellColor (int x, int y) {
        Cell cell = myCells.get(y).get(x);
        return myColorMap.get(cell.getMyCurrentState());
    }

    public ArrayList<ArrayList<Cell>> getMyCells () {
        return myCells;
    }

    public void setMyCells (ArrayList<ArrayList<Cell>> myCells) {
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
        Cell temp = myCells.get(currentY).get(currentX);
        myCells.get(currentY).set(currentX,myCells.get(swapeeY).get(swapeeX));
        myCells.get(currentY).get(currentX).setMyXCoordinate(currentX);
        myCells.get(currentY).get(currentX).setMyYCoordinate(currentY);
        myCells.get(swapeeY).set(currentX,temp);
        myCells.get(swapeeY).get(swapeeX).setMyXCoordinate(swapeeX);
        myCells.get(swapeeY).get(swapeeX).setMyYCoordinate(swapeeY);
    }

    public void replace (Cell updated, int updateX, int updateY) {
        myCells.get(updateY).set(updateX, updated);
    }
}
