import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

public class TriangleGridOfCells extends GridOfCells{

    public TriangleGridOfCells (Cell[][] cells, HashMap<Integer, Color> colorMap, NeighborProcessor edgeType, NeighborProcessor diagonalNeighbor) {
        super(cells, colorMap, edgeType, diagonalNeighbor);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Pair<Integer,Integer>> getSpecificNeighbors (int column, int row) {
        return super.getSpecificNeighbors(column, row);
    }
}
