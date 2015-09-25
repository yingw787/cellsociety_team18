import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import javafx.scene.shape.Shape;

public abstract class CellParser {

    public final String DEFAULT_STATE = "defaultState";
    public final String DEFAULT_SHAPE = "defaultShape";
    public final String DEFAULT_COLOR = "defaultColor";
    public final String DEFAULT_ANGLE = "defaultAngle";
    
    protected Element myCellConfigurationTag;
    protected int[] myBounds;
    protected String mySimulation;
    protected ResourceBundle myResource;
    protected String[] myCellTypesForState;
    
    
    public CellParser (Element gridConfigurationTag, String simulationType) {
        this.myCellConfigurationTag = (Element)gridConfigurationTag.getElementsByTagName("cellConfiguration").item(0);
        myBounds = new int[]{getDimension("breadth", gridConfigurationTag), 
                           getDimension("length", gridConfigurationTag)};
        this.mySimulation = simulationType;
        myResource = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getName());
        this.myCellTypesForState = myResource.getString(mySimulation).split(",");
    }
    
    private int getDimension(String direction, Element gridConfigurationTag){
        Element sizeTag = (Element)((Element)gridConfigurationTag.getElementsByTagName("gridProperties").item(0)).getElementsByTagName("size");
        return Integer.parseInt(sizeTag.getAttributes().getNamedItem(direction).getNodeValue());
    }
    
    abstract List<Cell> parseCells();
    
    /**
     * @Parameters properties: {state, x, y, angle}  
     *
     */
    protected Cell createCell (String mySimulation, String[] properties, Shape shape) {
        int state = Integer.parseInt(properties[0]);
        try {
            Constructor<?> c = Class.forName(myCellTypesForState[state]).getConstructor();
            return (Cell) c.newInstance(properties, shape);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Couldn't find Cell constructor", myCellTypesForState[state]);
        }
    }
}
