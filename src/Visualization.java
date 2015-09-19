import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Visualization extends GridPane {
	
	// the height of the visualization is pretty much whatever we decide it to be 
	private static final double VisualizationHeight = 500; 
	
	@SuppressWarnings("static-access")
	public GridPane init(int numberOfRows, int numberOfColumns, double visWidth){
		GridPane pane = new GridPane(); 
		
		// set the number of rows 
		for(int i = 0; i < numberOfRows; i++){
			HBox hbox = new HBox();
			RowConstraints row = new RowConstraints(VisualizationHeight/numberOfRows);
			pane.getRowConstraints().add(row);
			pane.addRow(i, hbox);
		}
		
		for(int i = 0; i < numberOfColumns; i++){
			ColumnConstraints column = new ColumnConstraints(visWidth/numberOfColumns);
			pane.getColumnConstraints().add(column);
		}
		
		for(int i = 0; i < numberOfRows; i++){
			for(int j = 0; j < numberOfColumns; j++){
				Rectangle tile = new Rectangle();
				tile.setFill(Color.BLUE);
				pane.setRowIndex(tile, i);
				pane.setColumnIndex(tile, j);
			}
		}
		
		pane.setGridLinesVisible(true);
		return pane;
		
	}
	
	
	
	public void step(double timeElapsed){
		
	}
	
	public double getVisualizationHeight(){
		return VisualizationHeight;
	}
	
	
	
	@SuppressWarnings({ "unused", "static-access" })
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
	
	@SuppressWarnings("unused")
	private void setColorOfRectangle(Rectangle tile, Paint color){
		tile.setFill(color);
	}

}
