import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

public abstract class NeighborProcessor {
    public abstract ArrayList<Pair<Integer, Integer>> process(int currentX, int currentY, ArrayList<Pair<Integer,Integer>> neighborPoints, int xmax, int ymax, ArrayList<ArrayList<Cell>> grid);
    public ArrayList<Pair<Integer, Integer>> defaultProcess (int currentX,
                                                      int currentY,
                                                      ArrayList<Pair<Integer, Integer>> neighborPoints,
                                                      int xmax,
                                                      int ymax,
                                                      ArrayList<ArrayList<Cell>> grid) {
        int x,y;
        Iterator<Pair<Integer,Integer>> i = neighborPoints.iterator();
        while (i.hasNext()) {
            Pair<Integer,Integer> p = i.next();
            x=(int) p.getKey();
            y=(int) p.getValue();
            if (checkCondition(currentX, currentY, xmax, ymax, x, y)) {
                i.remove();
            }
        }
        return neighborPoints;
    }
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return false;
    };
}
