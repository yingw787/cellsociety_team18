import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Visualization extends GridPane {
	
	// the height of the visualization is pretty much whatever we decide it to be 
	private static double VisualizationHeight;; 
	private static double VisualizationWidth; 
	
	@SuppressWarnings("static-access")
	public Scene init(int numberOfRows, int numberOfColumns, double visWidth, double visHeight){
		
		VisualizationHeight = visHeight; 
		VisualizationWidth = visWidth; 
		
		GridPane pane = new GridPane(); 
		
		// set the number of rows 
		for(int i = 0; i < numberOfRows; i++){
			RowConstraints row = new RowConstraints(VisualizationHeight/numberOfRows);
			pane.getRowConstraints().add(row);
		}
		
		for(int i = 0; i < numberOfColumns; i++){
			ColumnConstraints column = new ColumnConstraints(VisualizationWidth/numberOfColumns);
			pane.getColumnConstraints().add(column);
		}
		
		for(int i = 0; i < numberOfRows; i++){
			for(int j = 0; j < numberOfColumns; j++){
				Rectangle tile = new Rectangle(VisualizationHeight/numberOfRows, VisualizationWidth/numberOfColumns);
				tile.setFill(Color.RED);
				pane.add(tile, i, j);

			}
		}
		
		pane.setGridLinesVisible(true);
		Group root = new Group(); 
		root.getChildren().add(pane);
		Scene scene = new Scene(root);
		
		
		Rectangle testTile = getTile(2, 3, pane);
		setColorOfRectangle(testTile, Color.BLUE);
		
		return scene;
		
	}
	
	
	
	public void step(double timeElapsed){
		
	}
	
	public double getVisualizationHeight(){
		return VisualizationHeight;
	}
	
	public double getVisualizationWidth(){
		return VisualizationWidth; 
	}
	
	
	
	@SuppressWarnings("static-access")
	private Rectangle getTile(Integer rowIndex, Integer columnIndex, GridPane gridPane){
		Rectangle tile = null; 
		ObservableList<Node> children = gridPane.getChildren();
		for(Node node : children){
			if(gridPane.getRowIndex(node).equals(rowIndex) && gridPane.getColumnIndex(node).equals(columnIndex)){
				tile = (Rectangle) node; 
				break; 
			}
		}
		return tile;
	}
	
	private void setColorOfRectangle(Rectangle tile, Paint color){
		tile.setFill(color);
	}

}
