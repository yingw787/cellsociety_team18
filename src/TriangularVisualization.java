import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangularVisualization {

	private Integer myNumberOfRows, myNumberOfColumns; 
	private double myVisualizationWidth, myVisualizationHeight; 
	private HashMap<Integer[], Polygon> myPolygonHashMap = new HashMap<Integer[], Polygon>(); // stores the polygons for indexing 
	private Triangle myTriangleCreator = new Triangle();

	public TriangularVisualization(){

	}

	public Scene init(Integer numberOfRows, Integer numberOfColumns, double visualizationWidth, double visualizationHeight){

		myNumberOfRows = numberOfRows; 
		myNumberOfColumns = numberOfColumns; 
		myVisualizationWidth = visualizationWidth; 
		myVisualizationHeight = visualizationHeight; 


		Group group = new Group(); 

		for(Integer i = 0; i < numberOfRows; i++){
			Polygon triangle = myTriangleCreator.createTriangle(0, i);
			group.getChildren().add(triangle);
		}

		Scene scene = new Scene(group); 

		return scene; 
	}

	private class Triangle extends Polygon{

		// TODO: refactor later maybe? 
		private Polygon createTriangle(Integer rowIndex, Integer columnIndex){
			Polygon polygon = new Polygon();
			Double[] coordinatesIfOrientedUp = new Double[]{
					(columnIndex+0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex+1)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows) };
			Double[] coordinatesIfOrientedDown = new Double[]{
					columnIndex*(myVisualizationWidth/myNumberOfColumns), rowIndex*(myVisualizationHeight/myNumberOfRows),
					(columnIndex+1)*(myVisualizationWidth/myNumberOfColumns), rowIndex*(myVisualizationHeight/myNumberOfRows),
					(columnIndex+0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows) };
			Double[] coordinatesIfOrientedUpShifted = new Double[]{
					(columnIndex)*(myVisualizationWidth/myNumberOfColumns), (rowIndex)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex-0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex+0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows) };
			Double[] coordinatesIfOrientedDownShifted = new Double[]{
					(columnIndex-0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex+0.5)*(myVisualizationWidth/myNumberOfColumns), (rowIndex)*(myVisualizationHeight/myNumberOfRows),
					(columnIndex)*(myVisualizationWidth/myNumberOfColumns), (rowIndex+1)*(myVisualizationHeight/myNumberOfRows) };


			boolean evenRow = rowIndex % 2 == 0;
			boolean evenColumn = columnIndex % 2 == 0; 
			boolean oddRow = rowIndex % 2 == 1; 
			boolean oddColumn = columnIndex % 2 == 1; 

			if(evenRow & evenColumn){
				polygon.getPoints().addAll(coordinatesIfOrientedDown);
				polygon.setFill(Color.BLUE);
			}
			else if(evenRow & oddColumn){
				polygon.getPoints().addAll(coordinatesIfOrientedUpShifted);
				polygon.setFill(Color.RED);
			}
			else if(oddRow & evenColumn){
				polygon.getPoints().addAll(coordinatesIfOrientedUp);
				polygon.setFill(Color.GREEN);
			}
			else if(oddRow & oddColumn){
				polygon.getPoints().addAll(coordinatesIfOrientedDownShifted);
				polygon.setFill(Color.YELLOW);
			}

			Integer[] coordinates = new Integer[]{rowIndex, columnIndex};
			myPolygonHashMap.put(coordinates, polygon);
			return polygon; 
		}

	}


	// create a private class Triangle that generates triangles



}


