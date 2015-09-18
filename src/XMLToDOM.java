import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XMLToDOM {

	private GridOfCells myGridOfCells;
	private Simulation mySimulation;
	private Document myXMLfile;

	public XMLToDOM(Document doc){
		this.myXMLfile = doc;
	}

	public void createDOMfromXML(){
		mySimulation = createSimulationFromXML();
		Cell [][] newTwoDimensionalGrid = createTwoDimensionalGridWithCells();
		
		myGridOfCells = createGridOfCells(newTwoDimensionalGrid);
		mySimulation.setCellSocietyGrid(myGridOfCells);
		printGridAndSim();
	}
	
	private void printGridAndSim() {
		Cell[][] grid = myGridOfCells.getMyCells();
		for(int i=0; i<grid.length;i++){
			for(int j=0; j<grid[0].length; j++){
				System.out.print("loc: "+i+","+j+"\t");
				System.out.println("state: "+(grid[i][j]).getMyCurrentState());
			}
		}
//		mySimulation.print((mySimulation.getCellSocietyGrid()));
	}

	public Simulation createSimulationFromXML(){
		Element simulationParameters = (Element) myXMLfile.getElementsByTagName("parameters").item(0);
		mySimulation = createSimulationWithXMLRules(simulationParameters);
		return mySimulation;
	}
	
	
	
	private Cell[][] createTwoDimensionalGridWithCells(){
		Element gridProperties = (Element) ((Element) myXMLfile.getElementsByTagName("parameters").item(0)).getElementsByTagName("gridProperties").item(0);
		int breadth = Integer.parseInt(gridProperties.getElementsByTagName("breadth").item(0).getTextContent());
		int length = Integer.parseInt(gridProperties.getElementsByTagName("length").item(0).getTextContent());
		Cell[][] new2DArray = init2DArray(breadth, length);
		Element cellConfiguration = (Element) myXMLfile.getElementsByTagName("cellConfiguration").item(0);
		populateArrayWithCells(new2DArray, cellConfiguration);
		return new2DArray;
	}
	
	private Cell[][] populateArrayWithCells(Cell[][] newGrid, Element cellConfiguration){
		NodeList cells = cellConfiguration.getElementsByTagName("cell");
		for(int i = 0; i<cells.getLength(); i++){
			if(cells.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element cellElement = (Element) cells.item(i);
				createCellAndInsertInGrid(cellElement, newGrid);
			}
		}
		return newGrid;
	}

	private Cell[][] init2DArray(int breadth, int length) {
		Cell[][] initGrid = new Cell[breadth][length];
		for(int i=0; i<initGrid.length;i++){
			for(int j=0; j<initGrid[0].length; j++){
				Cell emptyCell = createEmptyCell(i,j);
				initGrid[i][j] = emptyCell;
			}
		}
		return initGrid;
	}

	private GridOfCells createGridOfCells(Cell [][] arrayOfCells) {
		Element gridProperties = (Element) ((Element) myXMLfile.getElementsByTagName("parameters").item(0)).getElementsByTagName("gridProperties").item(0);
		boolean wrap = Boolean.parseBoolean(gridProperties.getAttributes().getNamedItem("wrap").getNodeValue());
		int numNeighbors = Integer.parseInt(gridProperties.getAttributes().getNamedItem("numberOfGridNeighbors").getNodeValue());
		
		if(numNeighbors==8){
			return new GridOfCellsWithDiagonalNeighbors(arrayOfCells);
		}
		else if(numNeighbors==4){
			if(wrap){
				return new TorusOfCells(arrayOfCells);
			}
			else{
				return new GridOfCells(arrayOfCells);
			}
		}
		
		return null;
	}
	

	private Cell[][] createCellAndInsertInGrid(Element cellElement, Cell[][] initGrid) {
		Cell cell = createCell(cellElement);
		initGrid[cell.getMyXCoordinate()][cell.getMyYCoordinate()] = cell;
		return initGrid;
	}


	public Simulation getMySimulation() {
		return mySimulation;
	}

	public GridOfCells getMyGridOfCells() {
		return myGridOfCells;
	}
	
	abstract Cell createCell(Element cell);
	
	abstract Simulation createSimulationWithXMLRules(Element simulationParameters);
	
	public abstract Cell createEmptyCell(int x, int y);

}