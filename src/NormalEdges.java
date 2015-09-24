import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

public class NormalEdges extends NeighborProcessor{

    @Override
    public List<Pair<Integer, Integer>> process (int currentX, int currentY, List<Pair<Integer, Integer>> neighborPoints, int xlength, int ylength) {
        int x,y;
        Iterator<Pair<Integer,Integer>> i = neighborPoints.iterator();
        while (i.hasNext()) {
            Pair<Integer,Integer> p = i.next();
            x=(int) p.getKey();
            y=(int) p.getValue();
            if (!(y >= 0 && x >= 0 && y < ylength && x < xlength)) {
                i.remove();
            }
        }
        return  neighborPoints;
    }
}
