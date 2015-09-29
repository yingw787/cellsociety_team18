package neighbor;

import java.util.ArrayList;
import java.util.List;
import cell.Cell;
import javafx.util.Pair;


// TODO: Auto-generated Javadoc
/**
 * Allows the grid to expand whenever a neighbor is needed that is outside the boundaries of the
 * grid
 *
 * Not fully implemented yet.
 */
public class InfiniteEdges extends EdgeProcessor {

    /*
     * (non-Javadoc)
     *
     * @see NeighborProcessor#process(int, int, java.util.List, int, int, java.util.List)
     */
    @Override
    public ArrayList<Pair<Integer, Integer>> process (int currentX,
                                                      int currentY,
                                                      List<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax,
                                                      List<List<Cell>> grid) {
        // TODO Auto-generated method stub
        return null;
    }

}
