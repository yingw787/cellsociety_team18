import java.awt.Color;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class RectangleOrTriangleGridOfCells extends GridOfCells{

    public RectangleOrTriangleGridOfCells (List<List<Cell>> cells, Map<Integer, Color> colorMap, EdgeProcessor edgeType, NeighborDirectionProcessor directionNeighborProcessor) {
        super(cells, colorMap, edgeType, directionNeighborProcessor);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Pair<Integer,Integer>> getSpecificNeighbors (int column, int row){
        return super.getSpecificNeighbors(column, row);
    }
}
