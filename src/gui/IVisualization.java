package gui;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;

public interface IVisualization {

	public Scene init(double visualizationWidth, double visualizationHeight, boolean grid);
	
	public double getVisualizationHeight();
    
    public double getVisualizationWidth ();
    
    public Shape getShape(Integer rowIndex, Integer columnIndex); 
    
	public void drawCells(boolean grid);
	
}
