import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;

public class TorusOfCells extends GridOfCells{

    public TorusOfCells (Cell[][] cells, HashMap<Integer, Color> colorMap) {
        super(cells, colorMap);
    }
    public ArrayList<Cell> getNeighbors(int column, int row) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int y=row-1; y<=row+1; y+=2) {
            int adjustedY = torusWrapY(y);
            neighbors.add(getMyCells()[adjustedY][column]);
        }
        for (int x=column-1; x<=column+1; x+=2) {
            int adjustedX= torusWrapX(x);
            neighbors.add(getMyCells()[row][adjustedX]);
            //System.out.println("adjx: "+adjustedX);
        }
//        for (Cell c:neighbors) {
//        System.out.println("neigh"+c.getMyXCoordinate());
//        }
        return neighbors;
    }
}
