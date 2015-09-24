import java.util.ArrayList;
import java.util.Iterator;
import javafx.util.Pair;

public class NormalEdges extends NeighborProcessor{

    @Override
    public ArrayList<Pair<Integer, Integer>> process (int currentX, int currentY, ArrayList<Pair<Integer, Integer>> neighborPoints, int xlength, int ylength, ArrayList<ArrayList<Cell>> grid) {
        return defaultProcess(currentX,currentY,neighborPoints,xlength,ylength,grid);
    }
    @Override
    public boolean checkCondition(int currentX, int currentY, int xmax, int ymax, int x, int y) {
        return condition (xmax, ymax, x, y);
    }
    public boolean condition (int xlength, int ylength, int x, int y) {
        return !(y >= 0 && x >= 0 && y < ylength && x < xlength);
    }
    
}
