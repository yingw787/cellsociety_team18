package neighbor;
import java.util.List;

import cell.Cell;
import javafx.util.Pair;

/**
 * This class returns only neighbors in directly the N, E, S, and W directions.
 * Doesn't give meaningful results for the hexagonal grid.
 */
public class CardinalNeighbors extends NeighborDirectionProcessor{

    /* (non-Javadoc)
     * @see NeighborProcessor#process(int, int, java.util.List, int, int, java.util.List)
     */
    @Override
    public List<Pair<Integer, Integer>> process (int currentX,int currentY,List<Pair<Integer, Integer>> neighborPoints,int xmax,int ymax,List<List<Cell>> grid) {
        return defaultProcess(currentX,currentY,neighborPoints,xmax,ymax,grid);
    }
    
    /* (non-Javadoc)
     * @see NeighborProcessor#checkCondition(int, int, int, int, int, int)
     */
    @Override
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return condition (currentX, currentY, x, y);
    }
    
    /**
     * The condition that will be checked for the neighbor cell to be returned.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @param x the x
     * @param y the y
     * @return true, if successful
     */
    public boolean condition (int currentX, int currentY, int x, int y) {
        return!(x==currentX || y==currentY);
    }

}
