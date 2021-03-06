package parser;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cell.Cell;
import neighbor.NeighborProcessor;


/**
 */
public class GridOfCellsParser {

    private Element myGridConfigurationElement;
    private Element myGridPropertiesElement;
    private String mySimulationType;
    private ResourceBundle myResourceBundle;

    /**
     * Constructor for GridOfCellsParser.
     *
     * @param gridConfigurationElement Element
     * @param simulationType String
     * @param resource ResourceBundle
     */
    public GridOfCellsParser (Element gridConfigurationElement,
                              String simulationType,
                              ResourceBundle resource) {
        myGridConfigurationElement = gridConfigurationElement;
        myGridPropertiesElement =
                ((Element) myGridConfigurationElement.getElementsByTagName("gridProperties")
                        .item(0));
        mySimulationType = simulationType;
        myResourceBundle = resource;

    }

    /**
     * Method createEdgeNeighborProcessors.
     *
     * @param edge_neighborAttribute String
     * @return NeighborProcessor
     */
    public NeighborProcessor createEdgeNeighborProcessors (String edge_neighborAttribute) {
        String edge_neighborType =
                myGridPropertiesElement.getAttributes().getNamedItem(edge_neighborAttribute)
                        .getNodeValue();
        String edge_neighborTypeClassName =
                myResourceBundle.getString(edge_neighborAttribute + edge_neighborType);
        Constructor<?> c;
        try {
            c = Class.forName(edge_neighborTypeClassName).getConstructor();
            return (NeighborProcessor) c.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Error! " + edge_neighborAttribute + " of type" +
                                      edge_neighborType +
                                      " specified does not exist. Please check the properties files.");
        }
    }

    /**
     * Method createColorMap.
     *
     * @return Map<Integer,Color>
     */
    public Map<Integer, Color> createColorMap () {
        Element colorScheme =
                (Element) myGridPropertiesElement.getElementsByTagName("colorScheme").item(0);
        NodeList map = colorScheme.getElementsByTagName("map");
        Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
        for (int i = 0; i < map.getLength(); i++) {
            int state =
                    Integer.parseInt(map.item(i).getAttributes().getNamedItem("state")
                            .getNodeValue());
            Color color =
                    Color.decode(map.item(i).getAttributes().getNamedItem("color").getNodeValue());
            colorMap.put(state, color);
        }
        return colorMap;
    }

    /**
     * Method createGridCells.
     *
     * @return List<List<Cell>>
     */
    public List<List<Cell>> createGridCells () {
        CellFactory cf = new CellFactory(myGridConfigurationElement, mySimulationType);
        List<Cell> initCells = cf.getInitialCells();
        List<List<Cell>> gridCells = initGrid(cf.getGridBounds()[0], cf.getGridBounds()[1], cf);
        for (Cell c : initCells) {
            int x = c.getXCoordinate();
            int y = c.getYCoordinate();
            gridCells.get(x).set(y, c);
        }
        return gridCells;
    }

    /**
     * Method initGrid.
     *
     * @param x int
     * @param y int
     * @param cf CellFactory
     * @return List<List<Cell>>
     */
    private List<List<Cell>> initGrid (int x, int y, CellFactory cf) {
        ArrayList<List<Cell>> initGrid = new ArrayList<List<Cell>>();
        for (int i = 0; i < x; i++) {
            ArrayList<Cell> rowCells = new ArrayList<Cell>();
            for (int j = 0; j < y; j++) {
                String[] param =
                        new String[] { new Integer(Cell.EMPTY).toString(),
                                       new Integer(i).toString(), new Integer(j).toString(), "0",
                                       "0", "0" };
                Cell c = cf.getEmptyCell(param);
                rowCells.add(j, c);
            }
            initGrid.add(i, rowCells);
        }
        return initGrid;
    }

}
