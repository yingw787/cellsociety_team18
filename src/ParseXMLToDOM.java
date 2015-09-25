
import java.awt.Color;
import java.util.HashMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public abstract class ParseXMLToDOM {

    private GridOfCells myGridOfCells;
    private Simulation mySimulation;
    private Document myXMLfile;

    public ParseXMLToDOM (Document doc) {
        myXMLfile = doc;
    }

    public void createDOMfromXML () {
        Cell[][] newTwoDimensionalGrid = createTwoDimensionalGridWithCells();
        HashMap<Integer, Color> colorMap = createColorMap();
        myGridOfCells = createGridOfCells(newTwoDimensionalGrid, colorMap);
        mySimulation = createSimulationFromXML(myGridOfCells);
    }

    private HashMap<Integer, Color> createColorMap () {
        Element gridProperties =
                (Element) ((Element) getMyXMLfile().getElementsByTagName("parameters").item(0))
                        .getElementsByTagName("colorScheme").item(0);
        NodeList map = gridProperties.getElementsByTagName("map");
        HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>();
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

    public Simulation createSimulationFromXML (GridOfCells gridOfCells) {
        Element simulationParameters;
        String param = "parameters";
        simulationParameters = getTagWithName(param);
        mySimulation = createSimulationWithXMLRules(simulationParameters, gridOfCells);
        return mySimulation;
    }

    private Cell[][] createTwoDimensionalGridWithCells () {
        Element gridProperties =
                (Element) ((Element) getMyXMLfile().getElementsByTagName("parameters").item(0))
                        .getElementsByTagName("gridProperties").item(0);
        int breadth =
                Integer.parseInt(gridProperties.getElementsByTagName("breadth").item(0)
                        .getTextContent());
        int length =
                Integer.parseInt(gridProperties.getElementsByTagName("length").item(0)
                        .getTextContent());
        Cell[][] new2DArray = init2DArray(breadth, length);
        Element cellConfiguration;
        String param = "cellConfiguration";
        cellConfiguration = getTagWithName(param);
        populateArrayWithCells(new2DArray, cellConfiguration);
        return new2DArray;
    }

	private Element getTagWithName(String param) {
		Element cellConfiguration;
		cellConfiguration =
                (Element) getMyXMLfile().getElementsByTagName(param).item(0);
		return cellConfiguration;
	}

    private Cell[][] populateArrayWithCells (Cell[][] newGrid, Element cellConfiguration) {
        NodeList cells = cellConfiguration.getElementsByTagName("cell");
        for (int i = 0; i < cells.getLength(); i++) {
            if (cells.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element cellElement = (Element) cells.item(i);
                createCellAndInsertInGrid(cellElement, newGrid);
            }
        }
        return newGrid;
    }

    private Cell[][] init2DArray (int breadth, int length) {
        Cell[][] initGrid = new Cell[length][breadth];
        for (int y = 0; y < initGrid.length; y++) {
            for (int x = 0; x < initGrid[0].length; x++) {
                Cell emptyCell = createEmptyCell(x, y);
                initGrid[y][x] = emptyCell;
            }
        }
        return initGrid;
    }

    private GridOfCells createGridOfCells (Cell[][] arrayOfCells,
                                           HashMap<Integer, Color> colorMap) {
        Element gridProperties =
                (Element) ((Element) getMyXMLfile().getElementsByTagName("parameters").item(0))
                        .getElementsByTagName("gridProperties").item(0);
        boolean wrap =
                Boolean.parseBoolean(gridProperties.getAttributes().getNamedItem("wrap")
                        .getNodeValue());
        int numNeighbors =
                Integer.parseInt(gridProperties.getAttributes()
                        .getNamedItem("numberOfGridNeighbors").getNodeValue());
//        if (numNeighbors == 8) {
//            return new GridOfCellsWithDiagonalNeighbors(arrayOfCells, colorMap);
//        }
//        else if (numNeighbors == 4) {
//            if (wrap) {
//                return new TorusOfCells(arrayOfCells, colorMap);
//            }
//            else {
//                return new GridOfCells(arrayOfCells, colorMap);
//            }
//        }
        System.out.println("Error! No Grid Matches Properties Specified");
        return null;
    }

    private Cell[][] createCellAndInsertInGrid (Element cellElement, Cell[][] initGrid) {
        Cell cell = createCell(cellElement);
        initGrid[cell.getMyYCoordinate()][cell.getMyXCoordinate()] = cell;
        return initGrid;
    }

    public Simulation getMySimulation () {
        return mySimulation;
    }

    public GridOfCells getMyGridOfCells () {
        return myGridOfCells;
    }

    abstract Cell createCell (Element cell);

    abstract Simulation createSimulationWithXMLRules (Element simulationParameters,
                                                      GridOfCells gridOfCells);

    public abstract Cell createEmptyCell (int x, int y);

    public Document getMyXMLfile () {
        return myXMLfile;
    }

}
