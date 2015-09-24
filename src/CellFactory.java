import java.awt.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CellFactory {
	
	//Total number of States possible for the different simulations
//	fire, wator, life, segregation ..., ..., ...
	private int[] stateRangeForSim = {3,3,2,3,2,2,2};

	
	private final Shape defaultShape = new Rectangle();
	private final int defaultState = 0;
	private final int defaultStateRange = 2;
	private final Color defaultColor = Color.WHITE;
	
	private Document myDoc;
	private Element cellElements;
	private String simulationType;
	
	private int[] gridEdgeLimits = {0,0};


	public CellFactory(Document doc) {
		this.myDoc = doc;
		this.cellElements = (Element)((Element) myDoc.getElementsByTagName("gridConfiguration").item(0)).getElementsByTagName("cellConfiguration").item(0);
		this.simulationType = ((Element)myDoc.getElementsByTagName("simulation").item(0)).getAttributes().getNamedItem("type").getNodeValue();
		this.gridEdgeLimits[0] = (Integer.parseInt(((Element)((Element) myDoc.getElementsByTagName("gridConfiguration").item(0))
										.getElementsByTagName("gridProperties").item(0)).getElementsByTagName("size").item(0).getAttributes().getNamedItem("breadth").getNodeValue()));
		this.gridEdgeLimits[1] = (Integer.parseInt(((Element)((Element) myDoc.getElementsByTagName("gridConfiguration").item(0))
				.getElementsByTagName("gridProperties").item(0)).getElementsByTagName("size").item(0).getAttributes().getNamedItem("length").getNodeValue()));

	}

	public Cell[] parseCells(){
		NodeList cellList = cellElements.getElementsByTagName("cell");
		Cell[] cellsWithLocation = new Cell[cellList.getLength()];
		for (int i = 0; i < cellList.getLength(); i++) {
			if (cellList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element cellElement = (Element) cellList.item(i);
				// Parse based on initial configuration
				cellsWithLocation[i] = parseCellAttributes(cellElement);
			}
		}

		return cellsWithLocation;
	}

	public Cell[] randomCellAttributes(){
		int stateRange = getStateRange();
		int randomState;
		Cell[] cellsRandomLocation = new Cell[gridEdgeLimits[0]*gridEdgeLimits[1]];
		for(int i=0; i<gridEdgeLimits[0]; i++){
			for(int j=0; j<gridEdgeLimits[1]; j++){
				randomState = generateRandomInRange(1,stateRange);
				cellsRandomLocation[i] = createCell(randomState, i, j, defaultShape, defaultColor);
			}
		}
		return cellsRandomLocation;
	}
	
	private int generateRandomInRange(int min, int max){
		return min + (int)Math.random()*(max-min + 1);
	}

	private int getStateRange() {
		switch(simulationType){
		case "spreadingFire":
			return stateRangeForSim[0];
		case "waTor":
			return stateRangeForSim[1];
		case "gameOfLife":
			return stateRangeForSim[2];
		case "schellingSegregation":
			return stateRangeForSim[3];
			//		case " ":
			//			return new SchellingCell(state, xLocation, yLocation);
			//		case "":
			//			return new SchellingCell(state, xLocation, yLocation);
		default:
			System.out.println("Default state range being used");
			return defaultStateRange;
		}
	}

	public Cell[] probabilityBasedCellAttributes(){
		return null;
	}

	private Cell parseCellAttributes(Element cellElement) {
		int x = Integer.parseInt(((Element) cellElement.getElementsByTagName("location").item(0))
				.getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cellElement.getElementsByTagName("location").item(0))
				.getAttributes().getNamedItem("y").getNodeValue());
		if(x>gridEdgeLimits[0] || y>gridEdgeLimits[1]){
			throw new ParserException("Locations out of Grid bounds", x,y);
		}
		Text stateText= (Text) ((Element) cellElement.getElementsByTagName("state").item(0)).getChildNodes().item(0);
		int state;
		if(stateText==null){
			state = defaultState;
			System.out.println("State Empty. Using Default Value");
		}
		else{
			state = Integer.parseInt(stateText.getNodeValue());
		}

		String cellShape = ((Element) cellElement.getElementsByTagName("shape").item(0)).getTextContent();
		Shape shape;
		switch(cellShape){
		case "Rectangle": 
			shape = new Rectangle();
			break;

		case "Circle": 
			shape = new Circle();
			break;

//	*** Extract the image url using parsing  ***//
//		case  "Image":
//	      	ImageView image = ((Element) cellElement.getElementsByTagName("imageResource").item(0)).getTextContent();
//			System.out.println("Create Image object");
//			break;

		default: 
			shape = defaultShape;
		}
		return createCell(state, x, y, shape, null);
	}

	private Cell createCell(int state, int xLocation, int yLocation, Shape shape, Color color){
		switch(simulationType){
		case "spreadingFire":
			//			return new SchellingCell(xLocation, yLocation, repRate);
		case "waTor":
			if(state==FishCell.FISH){
//				return new FishCell(xLocation, yLocation, repRate);
			}
			else if(state==SharkCell.SHARK){
				//				return new SharkCell(state, xLocation, yLocation, gainEnergy, lossEnergy, repRate);
			}
		case "GameOfLife":
			return new TreeCell(state, xLocation, yLocation);
		case "schellingSegregation":
			return new SchellingCell(state, xLocation, yLocation);
			//		case " ":
			//			return new SchellingCell(state, xLocation, yLocation);
			//		case "":
			//			return new SchellingCell(state, xLocation, yLocation);
		default:
			throw new ParserException("Cell for this simulation not found!!", simulationType);
		}
	}

}
