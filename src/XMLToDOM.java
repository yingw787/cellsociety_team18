import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class XMLToDOM {

	private static Cell[][] myGridOfCells;
	private Simulation mySimulation;
	private Document myXMLfile;

	public XMLToDOM(Document doc){
		this.myXMLfile = doc;
		//		return Class.forName(validatorClass).newInstance();
	}

	public Simulation createDOMfromXML(){
		Element simulationParameters = (Element) myXMLfile.getElementsByTagName("parameters").item(0);
		Cell[][] initGrid = initGridOfCells(simulationParameters);
		boolean gridWrap = doesGridWrap(simulationParameters);
		
//		myGridOfCells = populateGridWithCells(initGrid, );
		mySimulation = createSimulationWithXMLRules(simulationParameters);
		return null;
	}

	abstract Simulation createSimulationWithXMLRules(Element simulationParameters);

	private boolean doesGridWrap(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("gridProperties").item(0);
		return Boolean.parseBoolean(gridProperties.getAttributes().getNamedItem("wrap").getNodeValue());
	}

	private Cell[][] initGridOfCells(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("gridProperties").item(0);
		int breadth = Integer.parseInt(gridProperties.getElementsByTagName("breadth").item(0).getTextContent());
		int length = Integer.parseInt(gridProperties.getElementsByTagName("length").item(0).getTextContent());
		return new Cell[breadth][length];
	}

	public void initCellGrid(int breadth, int lenght){
		myGridOfCells = new Cell[lenght][breadth];
	}

}