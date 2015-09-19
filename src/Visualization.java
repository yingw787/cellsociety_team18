
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Visualization extends GridPane {
	
	// the height of the visualization is pretty much whatever we decide it to be 
	private static double VisualizationHeight;
	private static double VisualizationWidth; 
	private Integer myNumberOfRows, myNumberOfColumns;
	private GridPane myPane;
	
	private GridOfCells myGridOfCells; 
	private Cell[][] my2DArrayOfCells;
	
	public Visualization(GridOfCells gridOfCells){
		myGridOfCells = gridOfCells; 
		my2DArrayOfCells = gridOfCells.getMyCells();
		
	}
	
	public Scene init(double visWidth, double visHeight){
		
		myNumberOfRows = my2DArrayOfCells.length;
		myNumberOfColumns = my2DArrayOfCells[0].length;
		
		VisualizationHeight = visHeight; 
		VisualizationWidth = visWidth; 
		
		myPane = new GridPane(); 
		
		// set the number of rows 
		for(int i = 0; i < myNumberOfRows; i++){
			RowConstraints row = new RowConstraints(VisualizationHeight/myNumberOfRows);
			myPane.getRowConstraints().add(row);
		}
		
		for(int i = 0; i < myNumberOfColumns; i++){
			ColumnConstraints column = new ColumnConstraints(VisualizationWidth/myNumberOfColumns);
			myPane.getColumnConstraints().add(column);
		}
	
		
		myPane.setGridLinesVisible(true);
		Group root = new Group(); 
		root.getChildren().add(myPane);
	              drawCells();
		Scene scene = new Scene(root);
		
		
//		Rectangle testTile = getTile(2, 3, pane);
//		setColorOfRectangle(testTile, Color.BLUE);
		
		return scene;
		
	}

	public void drawCells() {
	    Node node = myPane.getChildren().get(0);
	    myPane.getChildren().clear();
	    myPane.getChildren().add(0,node);
		for(int y = 0; y < myNumberOfRows; y++){
			for(int x = 0; x < myNumberOfColumns; x++){
				Rectangle tile = new Rectangle(VisualizationHeight/myNumberOfRows, VisualizationWidth/myNumberOfColumns);
				java.awt.Color awtColor = myGridOfCells.getCellColor(x, y) ;
				int r = awtColor.getRed();
				int g = awtColor.getGreen();
				int b = awtColor.getBlue();
				int a = awtColor.getAlpha();
				double opacity = a / 255.0 ;
				Color fxColor = Color.rgb(r, g, b, opacity);
				tile.setFill(fxColor);
				myPane.add(tile, x, y);
			}
		}
                
	}
	
	
	
	public void step(double timeElapsed){
		
	}
	
	public double getVisualizationHeight(){
		return VisualizationHeight;
	}
	
	public double getVisualizationWidth(){
		return VisualizationWidth; 
	}
	
	
	
//	@SuppressWarnings("static-access")
//	private Rectangle getTile(Integer rowIndex, Integer columnIndex, GridPane gridPane){
//		Rectangle tile = null; 
//		ObservableList<Node> children = gridPane.getChildren();
//		for(Node node : children){
//			if(gridPane.getRowIndex(node).equals(rowIndex) && gridPane.getColumnIndex(node).equals(columnIndex)){
//				tile = (Rectangle) node; 
//				break; 
//			}
//		}
//		return tile;
//	}
//	
//	private void setColorOfRectangle(Rectangle tile, Paint color){
//		tile.setFill(color);
//	}

}
