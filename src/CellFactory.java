import java.awt.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CellFactory {

	private Element cellElements;
	private Document myDoc;
	private String cellConfigType;
	
	private final String[] cellInitialConfig = {"location", "random", "probability"};

	
	
	public CellFactory(Element cellElements, Document doc, String cellConfigType) {
		this.cellElements = cellElements;
		this.myDoc = doc;
		this.cellConfigType = cellConfigType;
	}

	public Cell[] parseCells(){
		NodeList cellList = cellElements.getElementsByTagName("cell");
		Cell[] cells = new Cell[cellList.getLength()];
		for (int i = 0; i < cellList.getLength(); i++) {
			if (cellList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element cellElement = (Element) cellList.item(i);
				// Parse based on initial configuration
				cells[i] = parseCellAttributes(cellElement);
			}
		}

		return cells;
	}

	private Cell randomCellAttributes(){
		return null;
	}
	
	private Cell probabilityBasedCellAttributes(){
		return null;
	}
	
	private Cell parseCellAttributes(Element cellElement) {
		int x = Integer.parseInt(((Element) cellElement.getElementsByTagName("location").item(0))
						.getAttributes().getNamedItem("x").getNodeValue());
		int y = Integer.parseInt(((Element) cellElement.getElementsByTagName("location").item(0))
						.getAttributes().getNamedItem("y").getNodeValue());
		int state = Integer.parseInt(((Element) cellElement.getElementsByTagName("state").item(0))
						.getTextContent());
		String cellShape = ((Element) cellElement.getElementsByTagName("shape").item(0)).getTextContent();
//      ImageView image = ((Element) cellElement.getElementsByTagName("shape").item(0)).getTextContent();
		
		Shape shape;
		
		switch(cellShape){
		case "Rectangle": 
			shape = new Rectangle();
			break;
		case "Circle": 
			shape = new Circle();
			break;
		case  "Image":
			//Extract the image url using the parsing thing

			System.out.println("Create Image object");
			break;
		default: 
			//create default shape
			System.out.println("null");
		}
		String simulationType = ((Element)myDoc.getElementsByTagName("simulation").item(0)).getAttributes().getNamedItem("type").getNodeValue();
		return createCell(simulationType, state, x, y, null, null);
	}

	private Cell createCell(String simulation, int state, int xLocation, int yLocation, Shape shape, Color color){

		switch(simulation){
		case "spreadingFire":
			//			return new SchellingCell(xLocation, yLocation, repRate);
		case "waTor":
			if(state==FishCell.FISH){
				return new FishCell(state, xLocation, yLocation);
			}
			else if(state==SharkCell.SHARK){
				//				return new SharkCell(state, xLocation, yLocation, gainEnergy, lossEnergy, repRate);
			}
		case "gameOfLife":
			return new TreeCell(state, xLocation, yLocation);
		case "schellingSegregation":
			return new SchellingCell(state, xLocation, yLocation);
			//		case " ":
			//			return new SchellingCell(state, xLocation, yLocation);
			//		case "":
			//			return new SchellingCell(state, xLocation, yLocation);
		default:
			throw new ParserException("Cell for this simulation not found!!", simulation);
		}

	}

}
