package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import cell.Cell;


/**
 */
public class CellParserRandom extends CellParser {

    /**
     * Constructor for CellParserRandom.
     *
     * @param gridConfigurationTag Element
     * @param simulationType String
     * @param resourceBundle ResourceBundle
     * @param bounds int[]
     */
    public CellParserRandom (Element gridConfigurationTag,
                             String simulationType,
                             ResourceBundle resourceBundle,
                             int[] bounds) {
        super(gridConfigurationTag, simulationType, resourceBundle, bounds);
    }

    /**
     * Method parseCells.
     *
     * @return List<Cell>
     */
    @Override
    List<Cell> parseCells () {
        List<Cell> cells = new ArrayList<Cell>();
        int index;
        for (int i = 0; i < myBounds[0]; i++) {
            for (int j = 0; j < myBounds[1]; j++) {
                index = i * myBounds[0] + j;
                String[] properties = getCellProperties(i, j);
                // Shape shape = getShape();
                Cell newCell = createCell(properties, null);
                cells.add(index, newCell);
            }
        }
        return cells;
    }

    /**
     * // * Method getShape.
     *
     * @return Shape
     */
    // private Shape getShape(){
    // try {
    // Constructor<?> c = Class.forName(myResource.getString(this.DEFAULT_SHAPE)).getConstructor();
    // return (Shape) c.newInstance();
    // }
    // catch (Exception e) {
    // e.printStackTrace();
    // throw new ParserException("Could not create default shape for randomly genereated cell");
    // }
    // }

    /**
     * Method getCellProperties.
     *
     * @param X int
     * @param Y int
     * @return String[]
     */
    private String[] getCellProperties (int X, int Y) {
        String state = generateStateRandomly();
        String x = new Integer(X + 1).toString();
        String y = new Integer(Y + 1).toString();
        String angle = myResource.getString(DEFAULT_ANGLE);
        return new String[] { state, x, y, angle };
    }

    /**
     * Method generateStateRandomly.
     *
     * @return String
     */
    private String generateStateRandomly () {
        int numOfStates = myCellTypesForState.length;
        double random = Math.random();
        double chanceForState = 1.0 / numOfStates;
        for (int i = 0; i < numOfStates; i++) {
            if (random >= i * chanceForState && random < (i + 1) * chanceForState) {
                return new Integer(i).toString();
            }
        }
        return myResource.getString(DEFAULT_STATE);
    }

}
