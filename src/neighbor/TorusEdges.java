package neighbor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cell.Cell;
import javafx.util.Pair;


/**
 * Applies edges that terminate at wrap to the opposite side at the borders of the grid.
 */
public class TorusEdges extends EdgeProcessor {

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
        int x, y;
        Iterator<Pair<Integer, Integer>> i = neighborPoints.iterator();
        ArrayList<Pair<Integer, Integer>> torus = new ArrayList<Pair<Integer, Integer>>();
        while (i.hasNext()) {
            Pair<Integer, Integer> p = i.next();
            x = torusWrap(p.getKey(), xlength);
            y = torusWrap(p.getValue(), ylength);
            i.remove();
            torus.add(new Pair<Integer, Integer>(x, y));
        }
        return torus;
    }

    /**
     * Calculates the torus mapping of a coordinate.
     *
     * @param coordinate the coordinate
     * @param length the length
     * @return the new coordinate
     */
    public int torusWrap (int coordinate, int length) {
        if (coordinate < 0) {
            coordinate = length - 1;
        }
        else if (coordinate >= length) {
            coordinate = 0;
        }
        return coordinate;
    }
}
