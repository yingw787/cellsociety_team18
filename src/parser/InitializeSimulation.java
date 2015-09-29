package parser;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import grid.GridOfCells;
import simulation.Simulation;


public class InitializeSimulation {

    private static Simulation newSimulation;
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";


    public static void init (String fileName) throws ParserConfigurationException,
                                                                SAXException, IOException {
    	

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc =
                    dBuilder.parse((InitializeSimulation.class
                            .getResourceAsStream("/"+fileName)));

            Element gridConfigurationElement = (Element)doc.getElementsByTagName("gridConfiguration").item(0);
            String simulationType = ((Element)doc.getElementsByTagName("simulation").item(0)).getAttributes().getNamedItem("type").getNodeValue();
            GridOfCellsFactory gridFactory = new GridOfCellsFactory (gridConfigurationElement, simulationType);
            GridOfCells newGridOfCells = gridFactory.createGridOfCells();

            Element simulationElement = (Element)doc.getElementsByTagName("simulation").item(0);
            SimulationParserFactory mySimulationParserFactory = new SimulationParserFactory(simulationElement);
            newSimulation = mySimulationParserFactory.createSimulation(newGridOfCells);
//            System.out.println("Done Parsing");
            
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException(e, "XML file loading error!");
        }
    }


    /**
     * @return the newSimulation
     */
    public static Simulation getNewSimulation () {
        return newSimulation;
    }

}
