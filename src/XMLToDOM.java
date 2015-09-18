import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XMLToDOM {

	private static Cell[][] myGridOfCells;
	private Simulation mySimulation;
	private Document myXMLfile;

	public XMLToDOM(Document doc){
		this.myXMLfile = doc;
	}

	public void createDOMfromXML(){
		mySimulation = createSimulationFromXML();
		myGridOfCells = createGridFromXML();
	}
	
	public Simulation createSimulationFromXML(){
		Element simulationParameters = (Element) myXMLfile.getElementsByTagName("parameters").item(0);
		boolean gridWrap = doesGridWrap(simulationParameters);
		mySimulation = createSimulationWithXMLRules(simulationParameters);
		return mySimulation;
	}

	private Cell[][] createGridFromXML() {
		Element simulationParameters = (Element) myXMLfile.getElementsByTagName("parameters").item(0);
		Cell [][] initGrid = initGridOfCells(simulationParameters);
		Element cellConfiguration = (Element) myXMLfile.getElementsByTagName("cellConfiguration").item(0);
		return populateGridWithCells(initGrid, cellConfiguration);
	}

	private Cell[][] populateGridWithCells(Cell[][] initGrid, Element cellConfiguration){
		NodeList cells = cellConfiguration.getElementsByTagName("cell");
		for(int i = 0; i<cells.getLength(); i++){
			if(cells.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element cellElement = (Element) cells.item(i);
				Cell cell = createCellAndInsertInGrid(cellElement, initGrid);
System.out.println("grid: "+initGrid[cell.getMyXCoordinate()][cell.getMyYCoordinate()].getMyCurrentState());
			}
		}
		return initGrid;
	}
	
	abstract Cell createCellAndInsertInGrid(Element cell, Cell[][] initGrid);
	
	abstract Simulation createSimulationWithXMLRules(Element simulationParameters);

	private boolean doesGridWrap(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("gridProperties").item(0);
		return Boolean.parseBoolean(gridProperties.getAttributes().getNamedItem("wrap").getNodeValue());
	}

	private Cell[][] initGridOfCells(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("gridProperties").item(0);
		int breadth = Integer.parseInt(gridProperties.getElementsByTagName("breadth").item(0).getTextContent());
		int length = Integer.parseInt(gridProperties.getElementsByTagName("length").item(0).getTextContent());
//		Add for loop to create Cells that are "dead" -> have state -1 ???
		return new Cell[breadth][length];
	}

	public Simulation getMySimulation() {
		return mySimulation;
	}

	public static Cell[][] getMyGridOfCells() {
		return myGridOfCells;
	}

}