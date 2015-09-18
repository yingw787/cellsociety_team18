import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToWaTorDOMs extends XMLToDOM {

	private int breedingRateShark;
	private int breedingRateFish;
	private int initialEnergy;
	private int gainEnergy;
	
	public XMLToWaTorDOMs(Document doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		Element sharkRules = (Element) rules.getElementsByTagName("shark").item(0);
		breedingRateShark = Integer.parseInt(sharkRules.getAttributes().getNamedItem("breedingRate").getNodeValue());
		initialEnergy = Integer.parseInt(sharkRules.getAttributes().getNamedItem("initialEnergy").getNodeValue());
		gainEnergy = Integer.parseInt(sharkRules.getAttributes().getNamedItem("gainEnergy").getNodeValue());
		
		Element fishRules = (Element) rules.getElementsByTagName("fish").item(0);
		breedingRateFish = Integer.parseInt(fishRules.getAttributes().getNamedItem("breedingRate").getNodeValue());
		
		return new WaTorSimulation(breedingRateFish, breedingRateShark, initialEnergy, gainEnergy);
	}

	@Override
	Cell createCellAndInsertInGrid(Element cell, Cell[][] initGrid) {
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
		FishSharkCell fishShark = new FishSharkCell(state, x-1, y-1);
		initGrid[x-1][y-1] = fishShark;
		return fishShark;
	}

}
