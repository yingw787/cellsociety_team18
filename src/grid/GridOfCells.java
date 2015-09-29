package grid;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cell.Cell;
import javafx.util.Pair;
import neighbor.EdgeProcessor;
import neighbor.NeighborDirectionProcessor;
import neighbor.NeighborProcessor;


/**
 * The super class that holds the 2D list of cells and associated properties.
 */
public abstract class GridOfCells implements Iterable<Cell>{
    private List<List<Cell>> myCells;
    private List<Cell> emptyCells;
    private Map<Integer, Color> myColorMap;
    private NeighborProcessor myEdgeType, myDiagonalNeighbor;
    private String gridType;

    /**
     * Instantiates a new grid of cells.
     *
     * @param cells the cells
     * @param colorMap the color map
     * @param edgeType the edge type
     * @param diagonalNeighbor the diagonal neighbor
     */
    public GridOfCells (List<List<Cell>> cells, Map<Integer, Color> colorMap, EdgeProcessor edgeType, NeighborDirectionProcessor diagonalNeighbor) {
        myCells = cells;
        emptyCells = new ArrayList<Cell>();
        myColorMap = colorMap;
        for (List<Cell> myCellRow : myCells) {
            for (int x = 0; x < myCellRow.size(); x++) {
                if (myCellRow.get(x).getCurrentState() == Cell.EMPTY) {
                    emptyCells.add(myCellRow.get(x));
                }
            }
        }
        myEdgeType=edgeType;
        myDiagonalNeighbor=diagonalNeighbor;
    }
    public void setGridType(String s) {
    	gridType=s;
    }
    public String getGridType() {
    	return gridType;
    }
    /**
     * Gets the neighbors.
     *
     * @param column the column
     * @param row the row
     * @return the neighbors
     */
    public List<Cell> getNeighbors (int column, int row) {
        List<Pair<Integer,Integer>> neighborPoints = getSpecificNeighbors(column,row);
        neighborPoints=processNeighborPoints(neighborPoints,column,row);
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        for (Pair<Integer,Integer> p: neighborPoints) {
            neighbors.add(getCells().get(p.getValue()).get(p.getKey()));
        }
        return neighbors;
    }

    /**
     * Gets the specific neighbors to the type of grid (rectangular vs. hexagonal).
     *
     * @param column the column
     * @param row the row
     * @return the specific neighbors
     */
    public List<Pair<Integer,Integer>> getSpecificNeighbors(int column, int row) {
        List<Pair<Integer,Integer>> neighborPoints = new ArrayList<Pair<Integer,Integer>>();
        for (int y = row - 1; y <= row + 1; y++) {
            for (int x = column - 1; x <= column + 1; x++) {
                if (!(y == row && x == column)) {
                    neighborPoints.add(new Pair<Integer,Integer>(x,y));
                }
            }
        }
        return neighborPoints;
    }
    
    /**
     * Process neighbor points.
     *
     * @param neighborPoints the neighbor points
     * @param column the column
     * @param row the row
     * @return the list
     */
    public List<Pair<Integer, Integer>> processNeighborPoints(List<Pair<Integer,Integer>> neighborPoints, int column, int row) {
        neighborPoints=myEdgeType.process(column, row, neighborPoints,getCells().get(0).size(),getCells().size(), myCells);
        neighborPoints=myDiagonalNeighbor.process(column, row, neighborPoints,getCells().get(0).size(),getCells().size(), myCells);
        return neighborPoints;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Cell> iterator () {
        return new GridIterator(myCells);
    }

    /**
     * Gets the cell color map.
     *
     * @param x the x
     * @param y the y
     * @return the cell color
     */
    public Color getCellColor (int x, int y) {
        Cell cell = myCells.get(y).get(x);
        return myColorMap.get(cell.getCurrentState());
    }

    /**
     * Gets the cells.
     *
     * @return the cells
     */
    public List<List<Cell>> getCells () {
        return myCells;
    }

    /**
     * Sets the cells.
     *
     * @param cells the new cells
     */
    public void setCells (List<List<Cell>> cells) {
        this.myCells = cells;
    }

    /**
     * Change empty state.
     *
     * @param emptyCell the empty cell
     * @param newState the new state
     */
    public void changeEmptyState (Cell emptyCell, int newState) {
        emptyCell.setFutureState(newState);
    }

    /**
     * Dequeue random global empty.
     *
     * @return the cell
     */
    public Cell dequeueRandomGlobalEmpty () {
        int randomIndex = (int) (Math.random() * emptyCells.size());
        Cell result = emptyCells.get(randomIndex);
        emptyCells.remove(result);
        return result;
    }

    /**
     * Make state empty.
     *
     * @param currentCell the current cell
     */
    public void makeStateEmpty (Cell currentCell) {
        currentCell.setFutureState(Cell.EMPTY);
        emptyCells.add(currentCell);
    }



    /**
     * Swap cells at the specified locations.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @param swapeeX the swapee x
     * @param swapeeY the swapee y
     */
    public void swap (int currentX, int currentY, int swapeeX, int swapeeY) {
        Cell temp = myCells.get(currentY).get(currentX);
        myCells.get(currentY).set(currentX,myCells.get(swapeeY).get(swapeeX));
        myCells.get(currentY).get(currentX).setXCoordinate(currentX);
        myCells.get(currentY).get(currentX).setYCoordinate(currentY);
        myCells.get(swapeeY).set(currentX,temp);
        myCells.get(swapeeY).get(swapeeX).setXCoordinate(swapeeX);
        myCells.get(swapeeY).get(swapeeX).setYCoordinate(swapeeY);
    }

    /**
     * Replace cell at location with specified cell.
     *
     * @param updated the updated
     * @param updateX the update x
     * @param updateY the update y
     */
    public void replace (Cell updated, int updateX, int updateY) {
        myCells.get(updateY).set(updateX, updated);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return "GridOfCells [myCells=" + myCells.toString() + ", emptyCells=" + emptyCells.toString() + ", myColorMap=" +
               myColorMap.toString() + ", myEdgeType=" + myEdgeType.toString() + ", myDiagonalNeighbor=" +
               myDiagonalNeighbor.toString() + "]";
    }
}
