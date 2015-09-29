package grid;
import java.awt.Color;
import java.util.List;
import java.util.Map;

import cell.Cell;
import javafx.util.Pair;
import neighbor.EdgeProcessor;
import neighbor.NeighborDirectionProcessor;

/**
 * The original rectangle or triangle implementation of the grid.
 * Since we are using the triangle grid implementation that gives each cell 8 neighbors, the triangle grid
 * can be reduced to an identical rectangular grid so they share the same class.
 */
public class RectangleOrTriangleGridOfCells extends GridOfCells{

    /**
     * Instantiates a new rectangle or triangle grid of cells.
     *
     * @param cells the cells
     * @param colorMap the color map
     * @param edgeType the edge type
     * @param directionNeighborProcessor the direction neighbor processor
     */
    public RectangleOrTriangleGridOfCells (List<List<Cell>> cells, Map<Integer, Color> colorMap, EdgeProcessor edgeType, NeighborDirectionProcessor directionNeighborProcessor) {
        super(cells, colorMap, edgeType, directionNeighborProcessor);
    }

    /* (non-Javadoc)
     * @see GridOfCells#getSpecificNeighbors(int, int)
     */
    @Override
    public List<Pair<Integer,Integer>> getSpecificNeighbors (int column, int row){
        return super.getSpecificNeighbors(column, row);
    }
}
