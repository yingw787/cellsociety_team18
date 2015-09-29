public class VisualizationFactory {

	public Visualization createVisualizationGrid(String polygonName, Simulation simulation){
		
		Visualization visualization; 
		
		if(polygonName.equals("Rectangle")){
			visualization = new RectangularVisualization(simulation.getCellSocietyGrid()); 
			return visualization; 
		}
		else if(polygonName.equals("Triangle")){
			visualization = new TriangularVisualization(simulation.getCellSocietyGrid()); 
			return visualization; 
		}
		else if(polygonName.equals("Hexagon")){
			visualization = new HexagonalVisualization(simulation.getCellSocietyGrid()); 
			return visualization; 
		}
		return null; 
	}
	
	
	
}
