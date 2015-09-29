import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

/**
 * Super class for the processor class. Implemented this way to allow for polymorphism and shared code.
 */
public abstract class NeighborProcessor {
    
    /**
     * The default processing of neighbors.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @param neighborPoints the neighbor points
     * @param xmax the xmax
     * @param ymax the ymax
     * @param grid the grid
     * @return the list
     */
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
    
    
    /**
     * The main process method that must be overwritten.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @param neighborPoints the neighbor points
     * @param xmax the xmax
     * @param ymax the ymax
     * @param grid the grid
     * @return the list
     */
    public abstract List<Pair<Integer, Integer>> process(int currentX, int currentY, List<Pair<Integer,Integer>> neighborPoints, int xmax, int ymax, List<List<Cell>> grid);

    
    /**
     * Check condition or rules used to process. if true, the neighbor is removed.
     *
     * @param currentX the current x
     * @param currentY the current y
     * @param xmax the xmax
     * @param ymax the ymax
     * @param x the x
     * @param y the y
     * @return true, if successful
     */
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return false;
    };
}
