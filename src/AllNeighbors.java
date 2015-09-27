import java.util.List;
import javafx.util.Pair;

public class AllNeighbors extends NeighborDirectionProcessor{

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
