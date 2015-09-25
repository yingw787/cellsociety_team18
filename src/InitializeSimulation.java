import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class InitializeSimulation {

    // private final static String[] XMLFileNames = {"schellingSegregation.xml", "waTor.xml",
    // "spreadingFire.xml", "gameOfLife.xml"};
    // private final static String[] parserClassNames = {"XMLToSegregationDOMs", "XMLToWaTorDOMs",
    // "XMLToSpreadingFireDOMs", "XMLToGameOfLifeDOMs.xml"};

//    private static ParseXMLToDOM dataTransfer;

    public static void main (String args[]) throws ParserConfigurationException,
                                                                SAXException, IOException {

//        Map<String, String> map = new HashMap<String, String>();
//        map.put("schellingSegregation.xml", "XMLToSegregationDOMs");
//        map.put("waTor.xml", "XMLToWaTorDOMs");
//        map.put("spreadingFire.xml", "XMLToSpreadingFireDOMs");
//        map.put("gameOfLife.xml", "XMLToGameOfLifeDOMs");

        // change to resource file later
    	
    	String simulationDotXMLStringName = "test.xml";
    	

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc =
                    dBuilder.parse((InitializeSimulation.class
                            .getResourceAsStream(simulationDotXMLStringName)));
//Added
            String sim = ((Element)doc.getElementsByTagName("simulation").item(0)).getAttributes().getNamedItem("type").getNodeValue();
            System.out.println("Simulation: " + sim);
            
//            Element simulationElement = (Element)doc.getElementsByTagName("simulation").item(0);
//            SimulationParserFactory mySimulationParserFactory = new SimulationParserFactory(simulationElement);
//            Simulation newSimulation = mySimulationParserFactory.createSimulationParser();
//            System.out.println(newSimulation.toString());
            
//            GridOfCellsFactory myGridOfCellsFactory = new GridOfCellsFactory(doc);
//            myGridOfCellsFactory.createCellArray();
//            Constructor<?> c =
//                    Class.forName(map.get(simulationDotXMLStringName))
//                            .getConstructor(Document.class); // add type reference
//            dataTransfer = (ParseXMLToDOM) c.newInstance(doc);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException(e, "XML file loading error!");
        }
    }

//    public static ParseXMLToDOM getDataTransfer () {
//        return dataTransfer;
//    }
}
