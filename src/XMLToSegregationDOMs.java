import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToSegregationDOMs extends XMLToDOM {

	public XMLToSegregationDOMs(Document doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		double satisfactionThresh = Double.parseDouble(gridProperties.getElementsByTagName("satisfactionThreshold").item(0).getTextContent());
		return new SchellingSimulation(satisfactionThresh);
	}
	
	@Override
	Cell createCellAndInsertInGrid(Element cell, Cell[][] initGrid){
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
		SchellingCell schellingCell = new SchellingCell(state, x-1, y-1);
		initGrid[x-1][y-1] = schellingCell;
		return schellingCell;
		
	}

}
