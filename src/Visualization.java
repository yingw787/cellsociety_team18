import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Visualization extends GridPane {
	
	// the height of the visualization is pretty much whatever we decide it to be 
	private static final double VisualizationHeight = 500; 
	
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

}
