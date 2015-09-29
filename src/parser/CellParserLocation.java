package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import cell.Cell;


/**
 */
public class CellParserLocation extends CellParser {

    /**
     * Constructor for CellParserLocation.
     *
     * @param gridConfigurationTag Element
     * @param simulationType String
     * @param resourceBundle ResourceBundle
     * @param bounds int[]
     */
    public CellParserLocation (Element gridConfigurationTag,
                               String simulationType,
                               ResourceBundle resourceBundle,
                               int[] bounds) {
        super(gridConfigurationTag, simulationType, resourceBundle, bounds);
    }

    /**
     * Method parseCells. see super class
     *
     * @return List<Cell>
     */
    @Override
    List<Cell> parseCells () {
        List<Cell> cells = new ArrayList<Cell>();
        NodeList cellElements = myCellConfigurationTag.getElementsByTagName("cell");
        for (int i = 0; i < cellElements.getLength(); i++) {
            if (cellElements.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element cellElement = (Element) cellElements.item(i);
                String[] properties = getCellProperties(cellElement);
                // Cell newCell = this.createCell(properties, getShape(cellElement));
                Cell newCell = createCell(properties, null);
                cells.add(newCell);
            }
        }
        return cells;
    }

    /**
     * Method getCellProperties. Gets cell properties from the XML
     *
     * @param cellElement Element
     * @return String[]
     */
    private String[] getCellProperties (Element cellElement) {
        String state = getState(cellElement);
        String x = getCoordinate("x", cellElement);
        String y = getCoordinate("y", cellElement);
        if (numElements(cellElement) == 2) {
            return new String[] { state, x, y };
        }
        else if (numElements(cellElement) == 4) {
            String angle = getTagValue(cellElement, "angle");
            String patch = getTagValue(cellElement, "patch");
            return new String[] { state, x, y, patch, angle };
        }
        else if (numElements(cellElement) == 5) {
            String patch1 = getTagValue(cellElement, "patch1");
            String patch2 = getTagValue(cellElement, "patch2");
            String numAnts = getTagValue(cellElement, "numAnts");
            return new String[] { state, x, y, patch1, patch2, numAnts };
        }
        throw new ParserException("Cell configuration not recognized", cellElement);
    }

    /**
     * Method numElements.
     *
     * @param cellElement Element
     * @return int
     */
    private int numElements (Element cellElement) {
        NodeList n = cellElement.getChildNodes();
        return (n.getLength() - 1) / 2;
    }

    /**
     * Method getTagValue. Gets the value of the tag name
     *
     * @param cellElement Element
     * @param tagName String
     * @return String
     */
    private String getTagValue (Element cellElement, String tagName) {
        Text tagText =
                (Text) ((Element) cellElement.getElementsByTagName(tagName).item(0)).getChildNodes()
                        .item(0);
        if (tagText == null) {
            if (tagName.equals("angle")) {
                return myResource.getString(DEFAULT_ANGLE);
            }
            else {
                return myResource.getString(DEFAULT_PATCH);
            }
        }
        else {
            return tagText.getNodeValue();
        }
    }

    /**
     * Method getCoordinate.
     *
     * @param axis String
     * @param cellElement Element
     * @return String
     */
    private String getCoordinate (String axis, Element cellElement) {
        String coordinate =
                ((Element) cellElement.getElementsByTagName("location").item(0)).getAttributes()
                        .getNamedItem(axis).getNodeValue();
        if (Integer.parseInt(coordinate) > myBounds[0] ||
            Integer.parseInt(coordinate) > myBounds[1]) {
            throw new ParserException("Cell Index out of Bounds!", coordinate);
        }
        return coordinate;
    }

    /**
     * Method getState.
     *
     * @param cellElement Element
     * @return String
     */
    private String getState (Element cellElement) {
        Text stateText =
                (Text) ((Element) cellElement.getElementsByTagName("state").item(0)).getChildNodes()
                        .item(0);
        String state;
        if (stateText == null) {
            state = myResource.getString(DEFAULT_STATE);
        }
        else {
            state = stateText.getNodeValue();
        }
        return state;
    }

    // private Shape getShape(Element cellElement){
    // Text shapeText = (Text) ((Element)
    // cellElement.getElementsByTagName("shape").item(0)).getChildNodes().item(0);
    // Shape newShape;
    // Constructor<?> c;
    // try {
    // if(shapeText==null){
    // // return myResource.getString(this.DEFAULT_SHAPE);
    // c = Class.forName(myResource.getString(this.DEFAULT_SHAPE)).getConstructor();
    // newShape = (Shape) c.newInstance();
    // }
    // else{
    // // return shapeText.getNodeValue();
    // c = Class.forName(shapeText.getNodeValue()).getConstructor();
    // newShape = (Shape) c.newInstance();
    // }
    // }
    // catch (Exception e){
    // e.printStackTrace();
    // throw new ParserException("Error! Couldn't create Shape Object", shapeText);
    // }
    // return newShape;
    // }
}
