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
                                                      int ymax) {
        int x,y;
        Iterator<Pair<Integer,Integer>> i = neighborPoints.iterator();
        while (i.hasNext()) {
            Pair<Integer,Integer> p = i.next();
            x=(int) p.getKey();
            y=(int) p.getValue();
            if (!(x==currentX || y==currentY)) {
                i.remove();
            }
        }
        return neighborPoints;
    }

}
