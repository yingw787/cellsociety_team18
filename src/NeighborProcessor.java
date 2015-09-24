import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public abstract class NeighborProcessor {
    public abstract List<Pair<Integer, Integer>> process(int currentX, int currentY, List<Pair<Integer,Integer>> neighborPoints, int xmax, int ymax);
}
