import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class HexagonGridOfCells extends GridOfCells{

    public HexagonGridOfCells (Cell[][] cells, HashMap<Integer, Color> colorMap, NeighborProcessor edgeType, NeighborProcessor diagonalNeighbor) {
        super(cells, colorMap, edgeType, diagonalNeighbor);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getSpecificNeighbors (int column, int row) {
        ArrayList<Pair<Integer,Integer>> neighborPoints = new ArrayList<Pair<Integer,Integer>>();
        neighborPoints.add(new Pair<Integer,Integer>(column,row-1));
        neighborPoints.add(new Pair<Integer,Integer>(column+1,row));
        neighborPoints.add(new Pair<Integer,Integer>(column+1,row+1));
        neighborPoints.add(new Pair<Integer,Integer>(column,row+1));
        neighborPoints.add(new Pair<Integer,Integer>(column-1,row));
        neighborPoints.add(new Pair<Integer,Integer>(column-1,row+1));
        return neighborPoints;
    }
}
