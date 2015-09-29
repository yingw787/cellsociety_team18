import java.util.List;
import javafx.util.Pair;

/**
 * This class returns back all of the adjacent neighbors of a cell (8 for rectangle and triangle, 6 for hexagon).
 *
 * @author John
 */
public class AllNeighbors extends NeighborDirectionProcessor{

    /* (non-Javadoc)
     * @see NeighborProcessor#process(int, int, java.util.List, int, int, java.util.List)
     */
    @Override
    public List<Pair<Integer, Integer>> process (int currentX,
                                                      int currentY,
                                                      List<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax,
                                                      List<List<Cell>> grid) {
        return neighborPoints;
    }

}
