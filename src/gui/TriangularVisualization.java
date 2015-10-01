package gui;

import java.util.HashMap;
import java.util.List;
import cell.Cell;
import grid.GridOfCells;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;


public class TriangularVisualization extends Visualization implements IVisualization {

    private Integer myNumberOfRows, myNumberOfColumns;
    private double myVisualizationWidth, myVisualizationHeight;
    private HashMap<Integer[], Polygon> myPolygonHashMap = new HashMap<Integer[], Polygon>(); 

    private GridOfCells myGridOfCells;
    private List<List<Cell>> my2DArrayOfCells;

    private Group myGroup = new Group();

    public TriangularVisualization (GridOfCells gridOfCells) {
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
                Triangles newTriangle = new Triangles(myVisualizationWidth, 
                                                    myNumberOfColumns,
                                                   myVisualizationHeight, 
                                                   myNumberOfRows);
                Polygon triangle = newTriangle.createTriangle(i, j);
                myPolygonHashMap.put(new Integer[]{i,j}, triangle);
                java.awt.Color awtColor = myGridOfCells.getCellColor(i, j);
                int r = awtColor.getRed();
                int g = awtColor.getGreen();
                int b = awtColor.getBlue();
                int a = awtColor.getAlpha();
                double opacity = a / 255.0;
                Color fxColor = Color.rgb(r, g, b, opacity);
                triangle.setFill(fxColor);

                myGroup.getChildren().add(triangle);
            }

        }
    }

}
