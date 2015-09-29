package parser;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import cell.Cell;
import javafx.scene.shape.Shape;


/**
 */
public abstract class CellParser {

    public final String DEFAULT_STATE = "defaultState";
    public final String DEFAULT_SHAPE = "defaultShape";
    public final String DEFAULT_COLOR = "defaultColor";
    public final String DEFAULT_ANGLE = "defaultAngle";
    public final String DEFAULT_PATCH = "defaultPatch";

    protected Element myCellConfigurationTag;
    protected int[] myBounds;
    protected String mySimulation;
    protected ResourceBundle myResource;
    protected String[] myCellTypesForState;

    /**
     * Constructor for CellParser.
     *
     * @param gridConfigurationTag Element
     * @param simulationType String
     * @param resourceBundle ResourceBundle
     * @param bounds int[]
     */
    public CellParser (Element gridConfigurationTag,
                       String simulationType,
                       ResourceBundle resourceBundle,
                       int[] bounds) {
        myCellConfigurationTag =
                (Element) gridConfigurationTag.getElementsByTagName("cellConfiguration").item(0);
        myBounds = bounds;
        mySimulation = simulationType;
        myResource = resourceBundle;
        myCellTypesForState = myResource.getString(mySimulation).split(",");
    }

    /**
     * Method parseCells. Parse Cells from XML
     *
     * @return List<Cell>
     */
    abstract List<Cell> parseCells ();

    /**
     * Method createEmptyCell. Create an empty Cell of the type being used
     *
     * @param args String[]
     * @return Cell
     */
    public Cell createEmptyCell (String[] args) {
        return createCell(args, null);
    }

    /**
     *
     * @param properties String[]
     * @param shape Shape
     * @return Cell
     */
    protected Cell createCell (String[] properties, Shape shape) {
        int state = Integer.parseInt(properties[0]);
        try {
            Constructor<?> c =
                    Class.forName(myCellTypesForState[state]).getConstructor(String[].class);
            return (Cell) c.newInstance(new Object[] { properties });
            // return (Cell) c.newInstance(properties, shape);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Couldn't find Cell constructor", myCellTypesForState[state]);
        }
    }
}
