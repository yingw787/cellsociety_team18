import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class NeighborProcessorFactory {

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

    private Element myGridPropertiesTag;
    private ResourceBundle myResource;
    
    public NeighborProcessorFactory (Element gridPropertiesTag, ResourceBundle resource) {
        this.myGridPropertiesTag = gridPropertiesTag;
        this.myResource = resource;
    }
    
    /**
     * @Parameter: tag name of the neighborProcessor type wanted = "edgeType" | "neighborType"   
     *
     */
    public NeighborProcessor getNeighborProcessor(String processorType){
        String processorNumber = myGridPropertiesTag.getAttributes().getNamedItem(processorType).getNodeValue();
        String neighborProcesorClassName = myResource.getString(processorType+processorNumber);
        
        System.out.println("ProcessorType: "+processorType+"\t Name: "+neighborProcesorClassName);
        try {
            Constructor<?> c = Class.forName(neighborProcesorClassName).getConstructor();
            return (NeighborProcessor) c.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ParserException("Error! Neighbor Processor Type Not Found", neighborProcesorClassName);
        }
        
    }
    
}
