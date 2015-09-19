import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToWaTorDOMs extends ParseXMLToDOM {

	private int breedingRateShark;
	private int breedingRateFish;
	private int initialEnergy;
	private int gainEnergy;
	
	public XMLToWaTorDOMs(Document doc) {
		super(doc);
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters, GridOfCells gridOfCells) {
		Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		Element sharkRules = (Element) rules.getElementsByTagName("shark").item(0);
		breedingRateShark = Integer.parseInt(sharkRules.getAttributes().getNamedItem("breedingRate").getNodeValue());
		initialEnergy = Integer.parseInt(sharkRules.getAttributes().getNamedItem("initialEnergy").getNodeValue());
		gainEnergy = Integer.parseInt(sharkRules.getAttributes().getNamedItem("gainEnergy").getNodeValue());
		
		Element fishRules = (Element) rules.getElementsByTagName("fish").item(0);
		breedingRateFish = Integer.parseInt(fishRules.getAttributes().getNamedItem("breedingRate").getNodeValue());
		
		return new WaTorSimulation(gridOfCells, breedingRateFish, breedingRateShark, initialEnergy, gainEnergy);
	}

	//assumes simulation is created before the cells
	@Override
	Cell createCell(Element cell) {
		int x = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0)).getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0)).getTextContent());
		FishSharkCell fishShark;
		if (state==FishCell.FISH) {
		    fishShark = new FishCell(x-1, y-1, breedingRateFish);
		}
		else if (state==SharkCell.SHARK) {
		    fishShark = new SharkCell(x-1, y-1, breedingRateShark, initialEnergy, gainEnergy);
		}
		else {
		    fishShark = new FishSharkCell(x-1,y-1);
		}
		return fishShark;
	}

	@Override
	public Cell createEmptyCell(int x, int y) {
		return new FishSharkCell(Cell.EMPTY,x,y);
	}

}
