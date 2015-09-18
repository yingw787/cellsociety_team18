import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToSegregationDOMs extends XMLToDOM {

	public XMLToSegregationDOMs(Document doc) {
		super(doc);
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		double satisfactionThresh = Double.parseDouble(rules.getElementsByTagName("satisfactionThreshold").item(0).getTextContent());
		return new SchellingSimulation(satisfactionThresh);
	}

	@Override
	Cell createCell(Element cell){
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
		SchellingCell schellingCell = new SchellingCell(state, x-1, y-1);
		return schellingCell;
	}

	@Override
	public Cell createEmptyCell(int x, int y) {
		return new SchellingCell(Cell.EMPTY,x, y);
	}

}
