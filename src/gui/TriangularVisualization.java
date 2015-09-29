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
    private HashMap<Integer[], Polygon> myPolygonHashMap = new HashMap<Integer[], Polygon>(); // stores
 // the
 // polygons
 // for
 // indexing
    private Triangle myTriangleCreator = new Triangle();

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
                Polygon triangle = myTriangleCreator.createTriangle(i, j);

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

    private class Triangle extends Polygon {

        // TODO: refactor later maybe?
        private Polygon createTriangle (double rowIndex, double columnIndex) {
            Polygon polygon = new Polygon();
            // add in more comments
            Double[] coordinatesIfOrientedUp = new Double[] {
                                                              (columnIndex / 2 + 0.5) *
                                                              (myVisualizationWidth /
                                                               myNumberOfColumns),
                                                              (rowIndex) * (myVisualizationHeight /
                                                                            myNumberOfRows),
                                                              (columnIndex / 2) *
                                                                                             (myVisualizationWidth /
                                                                                              myNumberOfColumns),
                                                              (rowIndex +
                                                               1) * (myVisualizationHeight /
                                                                     myNumberOfRows),
                                                              (columnIndex / 2 + 1) *
                                                                                      (myVisualizationWidth /
                                                                                       myNumberOfColumns),
                                                              (rowIndex +
                                                               1) * (myVisualizationHeight /
                                                                     myNumberOfRows) };
            Double[] coordinatesIfOrientedDown = new Double[] {
                                                                (columnIndex / 2) *
                                                                (myVisualizationWidth /
                                                                 myNumberOfColumns),
                                                                rowIndex * (myVisualizationHeight /
                                                                            myNumberOfRows),
                                                                (columnIndex / 2 + 1) *
                                                                                             (myVisualizationWidth /
                                                                                              myNumberOfColumns),
                                                                rowIndex * (myVisualizationHeight /
                                                                            myNumberOfRows),
                                                                (columnIndex / 2 + 0.5) *
                                                                                             (myVisualizationWidth /
                                                                                              myNumberOfColumns),
                                                                (rowIndex +
                                                                 1) * (myVisualizationHeight /
                                                                       myNumberOfRows) };
            Double[] coordinatesIfOrientedUpShifted = new Double[] {
                                                                     ((columnIndex + 1) / 2) *
                                                                     (myVisualizationWidth /
                                                                      myNumberOfColumns),
                                                                     (rowIndex) * (myVisualizationHeight /
                                                                                   myNumberOfRows),
                                                                     ((columnIndex + 1) / 2 - 0.5) *
                                                                                                    (myVisualizationWidth /
                                                                                                     myNumberOfColumns),
                                                                     (rowIndex +
                                                                      1) * (myVisualizationHeight /
                                                                            myNumberOfRows),
                                                                     ((columnIndex + 1) / 2 + 0.5) *
                                                                                             (myVisualizationWidth /
                                                                                              myNumberOfColumns),
                                                                     (rowIndex +
                                                                      1) * (myVisualizationHeight /
                                                                            myNumberOfRows) };
            Double[] coordinatesIfOrientedDownShifted = new Double[] {
                                                                       ((columnIndex + 1) / 2 -
                                                                        0.5) *
                                                                       (myVisualizationWidth /
                                                                        myNumberOfColumns),
                                                                       (rowIndex) * (myVisualizationHeight /
                                                                                     myNumberOfRows),
                                                                       ((columnIndex + 1) / 2 +
                                                                        0.5) * (myVisualizationWidth /
                                                                                myNumberOfColumns),
                                                                       (rowIndex) * (myVisualizationHeight /
                                                                                     myNumberOfRows),
                                                                       ((columnIndex + 1) / 2) *
                                                                                                      (myVisualizationWidth /
                                                                                                       myNumberOfColumns),
                                                                       (rowIndex +
                                                                        1) * (myVisualizationHeight /
                                                                              myNumberOfRows) };

            boolean evenRow = (rowIndex % 2) == 0;
            boolean evenColumn = (columnIndex % 2) == 0;
            boolean oddRow = (rowIndex % 2) == 1;
            boolean oddColumn = (columnIndex % 2) == 1;

            if (evenRow & evenColumn) {
                polygon.getPoints().addAll(coordinatesIfOrientedDown);
                // polygon.setFill(Color.BLUE);
            }
            else if (evenRow & oddColumn) {
                polygon.getPoints().addAll(coordinatesIfOrientedUpShifted);
                // polygon.setFill(Color.RED);
            }
            else if (oddRow & evenColumn) {
                polygon.getPoints().addAll(coordinatesIfOrientedUp);
                // polygon.setFill(Color.GREEN);
            }
            else if (oddRow & oddColumn) {
                polygon.getPoints().addAll(coordinatesIfOrientedDownShifted);
                // polygon.setFill(Color.YELLOW);
            }

            Integer[] coordinates = new Integer[] { (int) rowIndex, (int) columnIndex };
            myPolygonHashMap.put(coordinates, polygon);
            return polygon;
        }

    }

    // create a private class Triangle that generates triangles

}
