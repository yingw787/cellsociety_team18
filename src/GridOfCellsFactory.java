import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class GridOfCellsFactory {

    private ResourceBundle myResource;
    private GridOfCellsParser myGridParser;
    private Element myGridConfigurationElement;
    
    public GridOfCellsFactory (Element gridConfigurationElement, String simulationType) {
        this.myResource = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getName());
        this.myGridConfigurationElement = gridConfigurationElement;
        this.myGridParser = new GridOfCellsParser(myGridConfigurationElement, simulationType, myResource);
    }

    public GridOfCells createGridOfCells(){
        NeighborProcessor edgeProcessor = myGridParser.createEdgeNeighborProcessors("edgeType");
        NeighborProcessor directionNeighborProcessor = myGridParser.createEdgeNeighborProcessors("directionType");
        return createGridOfCellsConstructor(myGridParser.createGridCells(), myGridParser.createColorMap(), edgeProcessor, directionNeighborProcessor);
    }

    private GridOfCells createGridOfCellsConstructor (List<List<Cell>> gridCells,
                                                      Map<Integer, Color> colorMap,
                                                      NeighborProcessor edgeProcessor,
                                                      NeighborProcessor directionNeighborProcessor) {
        String tagName = "gridShape";
        String gridShapeType = ((Element)myGridConfigurationElement.getElementsByTagName("gridProperties").item(0))
                                            .getAttributes().getNamedItem(tagName).getNodeValue();
        String gridShapeClassName = myResource.getString(tagName+gridShapeType);
        try {
            Constructor<?>c = Class.forName(gridShapeClassName).getConstructor(List.class, Map.class, NeighborProcessor.class, NeighborProcessor.class);
            return (GridOfCells) c.newInstance(gridCells, colorMap, edgeProcessor, directionNeighborProcessor);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Error! "+tagName+" of type"+gridShapeType+
                                      " specified does not exist. Please check the properties files.");
        }
    }

    
}
