import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToSpreadingFireDOMs extends XMLToDOM {

	public XMLToSpreadingFireDOMs(Document doc) {
		super(doc);
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		double catchFireProbability = Double.parseDouble(rules.getElementsByTagName("catchFireProbability").item(0).getTextContent());
		return new SpreadingFireSimulation(catchFireProbability);
	}

	@Override
	Cell createCell(Element cell) {
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
		TreeCell treeCell = new TreeCell(state, x-1, y-1);
		return treeCell;
	}

	@Override
	public Cell createEmptyCell() {
		return new TreeCell(0,0,0);
	}


}
