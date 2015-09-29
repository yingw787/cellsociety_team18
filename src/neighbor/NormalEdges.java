package neighbor;

import java.util.List;
import cell.Cell;
import javafx.util.Pair;


/**
 * Applies edges that terminate at the borders of the grid.
 */
public class NormalEdges extends EdgeProcessor {

    /*
     * (non-Javadoc)
     *
     * @see NeighborProcessor#process(int, int, java.util.List, int, int, java.util.List)
     */
    @Override
    public List<Pair<Integer, Integer>> process (int currentX,
                                                 int currentY,
                                                 List<Pair<Integer, Integer>> neighborPoints,
                                                 int xlength,
                                                 int ylength,
                                                 List<List<Cell>> grid) {
        return defaultProcess(currentX, currentY, neighborPoints, xlength, ylength, grid);
    }

    /*
     * (non-Javadoc)
     *
     * @see NeighborProcessor#checkCondition(int, int, int, int, int, int)
     */
    @Override
    public boolean checkCondition (int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return !(y >= 0 && x >= 0 && y < ymax && x < xmax);
    }

}
