import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.paint.Color;

public class GridOfCellsWithDiagonalNeighbors extends GridOfCells {

    public GridOfCellsWithDiagonalNeighbors (Cell[][] cells, HashMap<Integer,Color> colorMap) {
        super(cells, colorMap);
    }

    public ArrayList<Cell> getNeighbors(int column, int row) {
        ArrayList<Cell> neighbors=new ArrayList<Cell>();
        for (int y=row-1; y<=row+1; y++) {
            for (int x=column-1; x<=column+1; x++) {
                if (y>=0 && x>=0 && y<getMyCells().length && x<getMyCells()[0].length && !(y==row && x==column)) {
                    neighbors.add(getMyCells()[y][x]);
                }
            }
        }
        return neighbors;
    }
}
