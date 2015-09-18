import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToGameOfLifeDOMs extends XMLToDOM {

	public XMLToGameOfLifeDOMs(Document doc) {
		super(doc);
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		return null;
		//		return new GameOfLifeSimulation();
	}

	@Override
	Cell createCellAndInsertInGrid(Element cell, Cell[][] initGrid) {
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
//		ConwayCell conwayCell = new ConwayCell(state, x-1, y-1);
//		initGrid[x-1][y-1] = conwayCell;
//		return conwayCell;
		return null;
	}

}
