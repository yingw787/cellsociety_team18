import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class AllNeighbors extends NeighborProcessor{

    @Override
    public List<Pair<Integer, Integer>> process (int currentX,
                                                      int currentY,
                                                      List<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax,
                                                      ArrayList<ArrayList<Cell>> grid) {
        return neighborPoints;
    }

}
