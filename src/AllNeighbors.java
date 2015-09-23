import java.util.ArrayList;
import javafx.util.Pair;

public class AllNeighbors extends NeighborProcessor{

    @Override
    public ArrayList<Pair<Integer, Integer>> process (int currentX,
                                                      int currentY,
                                                      ArrayList<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax) {
        return neighborPoints;
    }

}
