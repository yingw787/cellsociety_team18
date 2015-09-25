
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class XMLToSpreadingFireDOMs extends ParseXMLToDOM {

    public XMLToSpreadingFireDOMs (Document doc) {
        super(doc);
    }

    @Override
    Simulation createSimulationWithXMLRules (Element simulationParameters,
                                             GridOfCells gridOfCells) {
        Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
        double catchFireProbability =
                Double.parseDouble(rules.getElementsByTagName("catchFireProbability").item(0)
                        .getTextContent());
//        return new SpreadingFireSimulation(gridOfCells, new double[]{catchFireProbability});
        return null;
    }

    @Override
    Cell createCell (Element cell) {
        int x =
                Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0))
                        .getAttributes().getNamedItem("x").getNodeValue());
        int y =
                Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0))
                        .getAttributes().getNamedItem("y").getNodeValue());
        int state =
                Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0))
                        .getTextContent());
        String cellShape = ((Element) cell.getElementsByTagName("shape").item(0)).getTextContent();
        
        Shape shape;
        switch(cellShape){
        	case "Rectangle": 
        		shape = new Rectangle();
        		break;
        	case "Circle": 
        		shape = new Circle();
        		break;
        	case  "Image":
        		System.out.println("Create Image object");
        		break;
        	default: 
        		//create default shape
        		System.out.println("null");
        }
        
        
        
        TreeCell treeCell = new TreeCell(state, x - 1, y - 1);
        return treeCell;
    }

    @Override
    public Cell createEmptyCell (int x, int y) {
        return new TreeCell(TreeCell.HEALTHY, x, y); // defaults to healthy tree?
    }

}
