import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

public abstract class NeighborProcessor {
    
    public List<Pair<Integer, Integer>> defaultProcess (int currentX,int currentY,List<Pair<Integer, Integer>> neighborPoints,int xmax,int ymax,List<List<Cell>> grid) {
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
    
    
    public abstract List<Pair<Integer, Integer>> process(int currentX, int currentY, List<Pair<Integer,Integer>> neighborPoints, int xmax, int ymax, List<List<Cell>> grid);

    
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return false;
    };
}
