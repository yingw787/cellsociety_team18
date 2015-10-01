// This entire file is part of my masterpiece.
// Inan Tainwala

package gui;

import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class Hexagons extends Polygon {

    Triangles newTriangle;
    public Hexagons(double myVisualizationWidth, 
                    double myNumberOfColumns,
                    double myVisualizationHeight, 
                    double myNumberOfRows){


        newTriangle = new Triangles(myVisualizationWidth, 
                                    myNumberOfColumns,
                                    myVisualizationHeight, 
                                    myNumberOfRows);
    }

    public Path createHexagon (Integer rowIndex, Integer columnIndex) {
        // have the hexagon made up of six triangles; create a subIndex for mapping triangle
        // indices with hexagon indices.

        boolean evenColumn = (columnIndex % 2) == 0; // need to tesselate based on even or odd
        // columns

        Path hexagon;

        if (evenColumn) {
            Polygon triangleOne =
                    newTriangle.createTriangle(rowIndex * 2 + 1, columnIndex * 3);
            Polygon triangleTwo =
                    newTriangle.createTriangle(rowIndex * 2 + 1, columnIndex * 3 + 1);
            Polygon triangleThree =
                    newTriangle.createTriangle(rowIndex * 2 + 1, columnIndex * 3 + 2);
            Polygon triangleFour =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3);
            Polygon triangleFive =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3 + 1);
            Polygon triangleSix =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3 + 2);

            Path tempOne, tempTwo, tempThree, tempFour;
            tempOne = (Path) union(triangleOne, triangleTwo);
            tempTwo = (Path) union(tempOne, triangleThree);
            tempThree = (Path) union(tempTwo, triangleFour);
            tempFour = (Path) union(tempThree, triangleFive);
            hexagon = (Path) union(tempFour, triangleSix);

        }
        else {
            Polygon triangleOne =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3);
            Polygon triangleTwo =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3 + 1);
            Polygon triangleThree =
                    newTriangle.createTriangle(rowIndex * 2 + 2, columnIndex * 3 + 2);
            Polygon triangleFour =
                    newTriangle.createTriangle(rowIndex * 2 + 3, columnIndex * 3);
            Polygon triangleFive =
                    newTriangle.createTriangle(rowIndex * 2 + 3, columnIndex * 3 + 1);
            Polygon triangleSix =
                    newTriangle.createTriangle(rowIndex * 2 + 3, columnIndex * 3 + 2);

            Path tempOne, tempTwo, tempThree, tempFour;
            tempOne = (Path) union(triangleOne, triangleTwo);
            tempTwo = (Path) union(tempOne, triangleThree);
            tempThree = (Path) union(tempTwo, triangleFour);
            tempFour = (Path) union(tempThree, triangleFive);
            hexagon = (Path) union(tempFour, triangleSix);

        }

        return hexagon;

    }

}