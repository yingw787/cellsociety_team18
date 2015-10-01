package gui;

import javafx.scene.shape.Polygon;

public class Triangles extends Polygon {

    double myVisualizationWidth;
    double myNumberOfColumns;
    double myVisualizationHeight; 
    double myNumberOfRows;
     
     // TODO: refactor later maybe?
     public Triangles(double myVisualizationWidth, 
                     double myNumberOfColumns,
                    double myVisualizationHeight, 
                    double myNumberOfRows){
         this.myVisualizationWidth = myVisualizationWidth;
         this.myNumberOfColumns =myNumberOfColumns;
        this.myVisualizationHeight= myVisualizationHeight;
        this.myNumberOfRows = myNumberOfRows;
     }
     public Polygon createTriangle (double rowIndex, double columnIndex) {
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
         return polygon;
     }

 }
