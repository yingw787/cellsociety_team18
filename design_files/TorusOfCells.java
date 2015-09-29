//
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//
//public class TorusOfCells extends GridOfCells {
//
//    public TorusOfCells (Cell[][] cells, Map<Integer, Color> colorMap) {
//        super(cells, colorMap);
//    }
//
//    @Override
//    public List<Cell> getNeighbors (int column, int row) {
//        List<Cell> neighbors = new ArrayList<Cell>();
//        for (int y = row - 1; y <= row + 1; y += 2) {
//            int adjustedY = torusWrapY(y);
//            neighbors.add(getMyCells()[adjustedY][column]);
//        }
//        for (int x = column - 1; x <= column + 1; x += 2) {
//            int adjustedX = torusWrapX(x);
//            neighbors.add(getMyCells()[row][adjustedX]);
//        }
//        return neighbors;
//    }
//    
//    public int torusWrapX (int coordinate) {
//        if (coordinate < 0) {
//            coordinate = getMyCells()[0].length - 1;
//        }
//        else if (coordinate >= getMyCells()[0].length) {
//            coordinate = 0;
//        }
//        return coordinate;
//    }
//
//    public int torusWrapY (int coordinate) {
//        if (coordinate < 0) {
//            coordinate = getMyCells().length - 1;
//        }
//        else if (coordinate >= getMyCells().length) {
//            coordinate = 0;
//        }
//        return coordinate;
//    }
//}
