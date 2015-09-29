package parser;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

import cell.Cell;
import grid.GridOfCells;
import grid.HexagonGridOfCells;
import grid.RectangleOrTriangleGridOfCells;
import neighbor.EdgeProcessor;
import neighbor.NeighborDirectionProcessor;
import neighbor.NeighborProcessor;

/**
 */
public class GridOfCellsFactory {

    private ResourceBundle myResource;
    private GridOfCellsParser myGridParser;
    private Element myGridConfigurationElement;
    
    /**
     * Constructor for GridOfCellsFactory.
     * @param gridConfigurationElement Element
     * @param simulationType String
     */
    public GridOfCellsFactory (Element gridConfigurationElement, String simulationType) {
        this.myResource = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getSimpleName());
        this.myGridConfigurationElement = gridConfigurationElement;
        this.myGridParser = new GridOfCellsParser(myGridConfigurationElement, simulationType, myResource);
    }

    /**
     * Method createGridOfCells. Created GridOfCells object
     * @return GridOfCells
     */
    public GridOfCells createGridOfCells(){
        List<List<Cell>> gridCells = myGridParser.createGridCells();
        NeighborProcessor edgeProcessor = myGridParser.createEdgeNeighborProcessors("edgeType");
        NeighborProcessor directionNeighborProcessor = myGridParser.createEdgeNeighborProcessors("directionType");
        Map<Integer, Color> colorMap = myGridParser.createColorMap();
        return createGridOfCellsConstructor(gridCells, colorMap, edgeProcessor, directionNeighborProcessor);
    }

    /**
     * Method createGridOfCellsConstructor. Creates the GridOfCells object based on shape of grid.
     * @param gridCells List<List<Cell>>
     * @param colorMap Map<Integer,Color>
     * @param edgeProcessor NeighborProcessor
     * @param directionNeighborProcessor NeighborProcessor
     * @return GridOfCells
     */
    private GridOfCells createGridOfCellsConstructor (List<List<Cell>> gridCells,
                                                      Map<Integer, Color> colorMap,
                                                      NeighborProcessor edgeProcessor,
                                                      NeighborProcessor directionNeighborProcessor) {
        String tagName = "gridShape";
        String gridShapeType = ((Element)myGridConfigurationElement.getElementsByTagName("gridProperties").item(0))
                                            .getAttributes().getNamedItem(tagName).getNodeValue();
        String gridShapeClassName = myResource.getString(tagName+gridShapeType);
        if(gridShapeClassName.equals("grid.RectangleOrTriangleGridOfCells")){
            GridOfCells g =new RectangleOrTriangleGridOfCells(gridCells, colorMap, (EdgeProcessor)edgeProcessor, (NeighborDirectionProcessor)directionNeighborProcessor);
            g.setGridType(gridShapeType);
            return g;
        }
        else if(gridShapeClassName.equals("grid.HexagonGridOfCells")){
        	GridOfCells g =new HexagonGridOfCells(gridCells, colorMap, (EdgeProcessor)edgeProcessor, (NeighborDirectionProcessor)directionNeighborProcessor);
            g.setGridType(gridShapeType);
            return g;
        }
        else{
            throw new ParserException("Error! "+tagName+" of type "+gridShapeType+
                                      " specified does not exist. Please check the properties files.");
        }
    }
}
