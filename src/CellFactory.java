import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class CellFactory {

    private Element myGridConfigurationTag; 
    private String mySimulationType;
    private ResourceBundle myResource;
    private int[] myGridBounds;
    private CellParser myCellParser;
    
    public CellFactory (Element gridConfigurationTag, String simulationType) {
        this.myGridConfigurationTag = gridConfigurationTag;
        this.mySimulationType = simulationType;
        this.myResource = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getName());
        this.myGridBounds = new int[]{getDimension("breadth", myGridConfigurationTag), 
                                      getDimension("length", myGridConfigurationTag)};
        this.myCellParser = getCellParser();
    }
    
    public Cell getEmptyCell(String[] args){
        return myCellParser.createEmptyCell(args);
    }

    public List<Cell> getInitialCells(){
        return myCellParser.parseCells();
    }
    
    public int[] getMyGridBounds () {
        return myGridBounds;
    }

    
    private int getDimension(String direction, Element gridConfigurationTag){
        Element sizeTag = (Element)((Element)gridConfigurationTag.getElementsByTagName("gridProperties").item(0)).getElementsByTagName("size").item(0);
        return Integer.parseInt(sizeTag.getAttributes().getNamedItem(direction).getNodeValue());
    }

    private CellParser getCellParser () {
        String typeOfInitialConfig = ((Element)myGridConfigurationTag.getElementsByTagName("cellConfiguration").item(0))
                .getAttributes().getNamedItem("type").getNodeValue();
        String typeOfCellConfigClassName = myResource.getString(typeOfInitialConfig);
        try {
            Constructor<?> c = Class.forName(typeOfCellConfigClassName).getConstructor(Element.class, String.class, ResourceBundle.class, int[].class);
            System.out.println(typeOfCellConfigClassName);
            return (CellParser) c.newInstance(myGridConfigurationTag, mySimulationType, myResource, myGridBounds);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Couldn't find Cell Parser for "+typeOfInitialConfig, typeOfCellConfigClassName);
        }
    }
}
