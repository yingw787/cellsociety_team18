import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

public class TorusEdges extends NeighborProcessor{
    @Override
    public ArrayList<Pair<Integer, Integer>> process (int currentX, int currentY, ArrayList<Pair<Integer, Integer>> neighborPoints, int xlength, int ylength, ArrayList<ArrayList<Cell>> grid) {
        int x,y;
        Iterator<Pair<Integer,Integer>> i = neighborPoints.iterator();
        ArrayList<Pair<Integer,Integer>> torus = new ArrayList<Pair<Integer,Integer>>();
        while (i.hasNext()) {
            Pair<Integer,Integer> p = i.next();
            x=torusWrap((int) p.getKey(),xlength);
            y=torusWrap((int) p.getValue(),ylength);
            i.remove();
            torus.add(new Pair<Integer,Integer>(x,y));
        }
        return  torus;
    }
    
    public int torusWrap (int coordinate, int xlength) {
        if (coordinate < 0) {
            coordinate = xlength - 1;
        }
        else if (coordinate >= xlength) {
            coordinate = 0;
        }
        return coordinate;
    }
}

