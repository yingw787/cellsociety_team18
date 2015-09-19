import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToSpreadingFireDOMs extends ParseXMLToDOM {

	public XMLToSpreadingFireDOMs(Document doc) {
		super(doc);
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters, GridOfCells gridOfCells) {
		Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		double catchFireProbability = Double.parseDouble(rules.getElementsByTagName("catchFireProbability").item(0).getTextContent());
		return new SpreadingFireSimulation(gridOfCells, catchFireProbability);
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
	public Cell createEmptyCell(int x, int y) {
		return new TreeCell(TreeCell.HEALTHY, x,y); //defaults to healthy tree?
	}


}
