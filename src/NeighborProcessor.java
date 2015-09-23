import java.util.ArrayList;
import javafx.util.Pair;

public abstract class NeighborProcessor {
    public abstract ArrayList<Pair<Integer, Integer>> process(int currentX, int currentY, ArrayList<Pair<Integer,Integer>> neighborPoints, int xmax, int ymax);
}
