package parser;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

import cell.Cell;

/**
 */
public class CellFactory {

    private Element myGridConfigurationTag; 
    private String mySimulationType;
    private ResourceBundle myResource;
    private int[] myGridBounds;
    private CellParser myCellParser;
    
    /**
     * Constructor for CellFactory.
     * @param gridConfigurationTag Element 
     * @param simulationType String
     */
    public CellFactory (Element gridConfigurationTag, String simulationType) {
        this.myGridConfigurationTag = gridConfigurationTag;
        this.mySimulationType = simulationType;
        this.myResource = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getSimpleName());
        this.myGridBounds = new int[]{getDimension("breadth", myGridConfigurationTag), 
                                      getDimension("length", myGridConfigurationTag)};
        this.myCellParser = getCellParser();
    }
    
    /**
     * Method getEmptyCell. Cells of the type of Cell required
     * @param args String[]
     * @return Cell
     */
    public Cell getEmptyCell(String[] args){
        return myCellParser.createEmptyCell(args);
    }

    /**
     * Method getInitialCells Cells from the XML
     * @return List<Cell>
     */
    public List<Cell> getInitialCells(){
        return myCellParser.parseCells();
    }
    
    /**
     * Method getMyGridBounds. Bounds of the grid
     * @return int[]
     */
    public int[] getGridBounds () {
        return myGridBounds;
    }

    
    /**
     * Method getDimension. Gets X or Y dimension of the grid from XML
     * @param direction String
     * @param gridConfigurationTag Element
     * @return int
     */
    private int getDimension(String direction, Element gridConfigurationTag){
        Element sizeTag = (Element)((Element)gridConfigurationTag.getElementsByTagName("gridProperties").item(0)).getElementsByTagName("size").item(0);
        return Integer.parseInt(sizeTag.getAttributes().getNamedItem(direction).getNodeValue());
    }

    /**
     * Method getCellParser. Creates a Cell Parser based on whether the initial configuration of the cells is random or location based
     * @return CellParser
     */
    private CellParser getCellParser () {
        String typeOfInitialConfig = ((Element)myGridConfigurationTag.getElementsByTagName("cellConfiguration").item(0))
                .getAttributes().getNamedItem("type").getNodeValue();
        String typeOfCellConfigClassName = myResource.getString(typeOfInitialConfig);
        try {
            Constructor<?> c = Class.forName(typeOfCellConfigClassName).getConstructor(Element.class, String.class, ResourceBundle.class, int[].class);
            return (CellParser) c.newInstance(myGridConfigurationTag, mySimulationType, myResource, myGridBounds);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Couldn't find Cell Parser for "+typeOfInitialConfig, typeOfCellConfigClassName);
        }
    }
}
