import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

public class CardinalNeighbors extends NeighborProcessor{

    @Override
    public List<Pair<Integer, Integer>> process (int currentX,
                                                      int currentY,
                                                      List<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax,
                                                      ArrayList<ArrayList<Cell>> grid) {
        return defaultProcess(currentX,currentY,neighborPoints,xmax,ymax,grid);
    }
    @Override
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return condition (currentX, currentY, x, y);
    }
    public boolean condition (int currentX, int currentY, int x, int y) {
        return!(x==currentX || y==currentY);
    }

}
