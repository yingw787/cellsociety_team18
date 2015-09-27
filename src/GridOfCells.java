
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;


public abstract class GridOfCells{
    private List<List<Cell>> myCells;
    private List<Cell> emptyCells;
    private Map<Integer, Color> myColorMap;
    private NeighborProcessor myEdgeType, myDiagonalNeighbor;


    public GridOfCells (List<List<Cell>> cells, Map<Integer, Color> colorMap, EdgeProcessor edgeType, NeighborDirectionProcessor diagonalNeighbor) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        myColorMap = colorMap;
        for (List<Cell> myCellRow : myCells) {
            for (int x = 0; x < myCellRow.size(); x++) {
                if (myCellRow.get(x).getMyCurrentState() == Cell.EMPTY) {
                    emptyCells.add(myCellRow.get(x));
                }
            }
        }
        myEdgeType=edgeType;
        myDiagonalNeighbor=diagonalNeighbor;
    }

    public List<Cell> getNeighbors (int column, int row) {
        List<Pair<Integer,Integer>> neighborPoints = getSpecificNeighbors(column,row);
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

    public List<Pair<Integer,Integer>> getSpecificNeighbors(int column, int row) {
        List<Pair<Integer,Integer>> neighborPoints = new ArrayList<Pair<Integer,Integer>>();
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
    
    public List<Pair<Integer, Integer>> processNeighborPoints(List<Pair<Integer,Integer>> neighborPoints, int column, int row) {
        neighborPoints=myEdgeType.process(column, row, neighborPoints,getMyCells().get(0).size(),getMyCells().size(), myCells);
        neighborPoints=myDiagonalNeighbor.process(column, row, neighborPoints,getMyCells().get(0).size(),getMyCells().size(), myCells);
        return neighborPoints;
    }
    
    public Color getCellColor (int x, int y) {
        Cell cell = myCells.get(y).get(x);
        return myColorMap.get(cell.getMyCurrentState());
    }

    public List<List<Cell>> getMyCells () {
        return myCells;
    }

    public void setMyCells (List<List<Cell>> myCells) {
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
