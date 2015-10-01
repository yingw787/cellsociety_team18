// This entire file is part of my masterpiece.
// Inan Tainwala

package gui;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cell.Cell;
import grid.GridOfCells;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;


public class HexagonalVisualization extends Visualization implements IVisualization {

    private Integer myNumberOfRows, myNumberOfColumns;
    private double myVisualizationWidth, myVisualizationHeight;
    private Map<Integer[], Path> myPolygonHashMap = new HashMap<Integer[], Path>(); 
    private Group myGroup = new Group();

    private GridOfCells myGridOfCells;
    private List<List<Cell>> my2DArrayOfCells;

    public HexagonalVisualization (GridOfCells gridOfCells) {
        myGridOfCells = gridOfCells;
        my2DArrayOfCells = gridOfCells.getCells();
    }

    @Override
    public Scene init (double visualizationWidth, double visualizationHeight, boolean grid) {

        myNumberOfRows = my2DArrayOfCells.size();
        myNumberOfColumns = my2DArrayOfCells.get(0).size();

        myVisualizationWidth = visualizationWidth;
        myVisualizationHeight = visualizationHeight;

        Scene scene = new Scene(myGroup);

        drawCells(grid);

        return scene;
    }

    @Override
    public double getVisualizationHeight () {
        return myVisualizationHeight;
    }

    @Override
    public double getVisualizationWidth () {
        return myVisualizationWidth;
    }

    @Override
    public Shape getShape (Integer rowIndex, Integer columnIndex) {
        Integer[] coordinates = new Integer[] { (int) rowIndex, (int) columnIndex };
        return myPolygonHashMap.get(coordinates);

    }

    @Override
    public void drawCells (boolean grid) {

        for (Integer i = 0; i < myNumberOfRows; i++) {
            for (Integer j = 0; j < myNumberOfColumns; j++) {
                Hexagons hexagons = new Hexagons(myVisualizationWidth, 
                                                 myNumberOfColumns,
                                                 myVisualizationHeight, 
                                                 myNumberOfRows);
                Path hexagon = hexagons.createHexagon(i, j);
                Integer[] coordinates = new Integer[] {i, j};
                myPolygonHashMap.put(coordinates, hexagon);
                java.awt.Color awtColor = myGridOfCells.getCellColor(i, j);
                int r = awtColor.getRed();
                int g = awtColor.getGreen();
                int b = awtColor.getBlue();
                int a = awtColor.getAlpha();
                double opacity = a / 255.0;
                Color fxColor = Color.rgb(r, g, b, opacity);

                hexagon.setFill(fxColor);
                myGroup.getChildren().add(hexagon);
            }
        }

    }

}
