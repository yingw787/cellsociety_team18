import java.util.HashMap;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


// TODO: implement interface IVisualization 
public class RectangularVisualization extends Visualization implements IVisualization{

	private double myVisualizationHeight;
    private double myVisualizationWidth;
    private Integer myNumberOfRows, myNumberOfColumns;
    private GridPane myPane;
    private HashMap<Integer[], Shape> myPolygonHashMap = new HashMap<Integer[], Shape>(); 
	
    private GridOfCells myGridOfCells;
    private List<List<Cell>> my2DArrayOfCells;
    
    
    // TODO: add in GridOfCells later as an argument 
   
    public RectangularVisualization(GridOfCells gridOfCells){ 
    	myGridOfCells = gridOfCells;
        my2DArrayOfCells = gridOfCells.getMyCells();
    }
    
	@Override 
	public Scene init(double visualizationWidth, double visualizationHeight, boolean grid){
		
		// TODO: remove numberOfRows and numberOfColumns to integrate with GridOfCells and RectangularVisualization 
		myVisualizationHeight = visualizationHeight; 
		myVisualizationWidth = visualizationWidth; 
		
		myNumberOfRows = my2DArrayOfCells.size();
        myNumberOfColumns = my2DArrayOfCells.get(0).size();
		
		myPane = new GridPane(); 
		
		for (int i = 0; i < myNumberOfRows; i++) {
            RowConstraints row = new RowConstraints(myVisualizationHeight / myNumberOfRows);
            myPane.getRowConstraints().add(row);
        }

        for (int i = 0; i < myNumberOfColumns; i++) {
            ColumnConstraints column =
                    new ColumnConstraints(myVisualizationWidth / myNumberOfColumns);
            myPane.getColumnConstraints().add(column);
        }
        
        myPane.setGridLinesVisible(true);
        Group root = new Group();
        root.getChildren().add(myPane);
        drawCells(grid);
        Scene scene = new Scene(root);
        
        return scene; 
		
		
	}
	
	public void drawCells(boolean grid) {
        
        myPane.getChildren().clear();
        for (int y = 0; y < myNumberOfRows; y++) {
            for (int x = 0; x < myNumberOfColumns; x++) {
                Shape tile =
                        new Rectangle(myVisualizationHeight / myNumberOfRows,
                                      myVisualizationWidth / myNumberOfColumns);
                java.awt.Color awtColor = myGridOfCells.getCellColor(x, y);
                int r = awtColor.getRed();
                int g = awtColor.getGreen();
                int b = awtColor.getBlue();
                int a = awtColor.getAlpha();
                double opacity = a / 255.0;
                Color fxColor = Color.rgb(r, g, b, opacity);
                tile.setFill(fxColor);
                myPane.add(tile, x, y);
                
                Integer[] coordinates = new Integer[]{x, y};
    			myPolygonHashMap.put(coordinates, tile);
                
                
            }
        }
        myPane.setGridLinesVisible(grid);

    }
	
	
	public double getVisualizationHeight () {
        return myVisualizationHeight;
    }

    public double getVisualizationWidth () {
        return myVisualizationWidth;
    }
	
    public Shape getShape(Integer rowIndex, Integer columnIndex){
		Integer[] coordinates = new Integer[]{(int) rowIndex, (int) columnIndex};
		return myPolygonHashMap.get(coordinates);	
	}
	
}
