import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class HexagonGridOfCells extends GridOfCells{

    public HexagonGridOfCells (List<List<Cell>> cells, Map<Integer, Color> colorMap, EdgeProcessor edgeType, NeighborDirectionProcessor diagonalNeighbor) {
        super(cells, colorMap, edgeType, diagonalNeighbor);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<Pair<Integer, Integer>> getSpecificNeighbors (int column, int row) {
        List<Pair<Integer,Integer>> neighborPoints = new ArrayList<Pair<Integer,Integer>>();
        neighborPoints.add(new Pair<Integer,Integer>(column,row-1));
        neighborPoints.add(new Pair<Integer,Integer>(column+1,row));
        neighborPoints.add(new Pair<Integer,Integer>(column+1,row+1));
        neighborPoints.add(new Pair<Integer,Integer>(column,row+1));
        neighborPoints.add(new Pair<Integer,Integer>(column-1,row));
        neighborPoints.add(new Pair<Integer,Integer>(column-1,row+1));
        return neighborPoints;
    }
}
