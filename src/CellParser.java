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
    
    
    public CellParser (Element gridConfigurationTag, String simulationType, ResourceBundle resourceBundle, int[] bounds) {
        this.myCellConfigurationTag = (Element)gridConfigurationTag.getElementsByTagName("cellConfiguration").item(0);
        this.myBounds = bounds;
        this.mySimulation = simulationType;
        this.myResource = resourceBundle;
        this.myCellTypesForState = myResource.getString(mySimulation).split(",");
    }
    
    abstract List<Cell> parseCells();
    
    public Cell createEmptyCell(String[] args){
        return createCell(args, null);
    }
    
    /**
     * @Parameters properties: {state, x, y, angle}  
     *
     */
    protected Cell createCell (String[] properties, Shape shape) {
        int state = Integer.parseInt(properties[0]);
        try {
            Constructor<?> c = Class.forName(myCellTypesForState[state]).getConstructor(String[].class);
            return (Cell) c.newInstance(new Object[]{properties});
//            return (Cell) c.newInstance(properties, shape);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Couldn't find Cell constructor", myCellTypesForState[state]);
        }
    }
}
