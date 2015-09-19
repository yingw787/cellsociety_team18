import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class InitializeSimulation {

	//private final static String[] XMLFileNames = {"schellingSegregation.xml", "waTor.xml", "spreadingFire.xml", "gameOfLife.xml"};
	//private final static String[] parserClassNames = {"XMLToSegregationDOMs", "XMLToWaTorDOMs", "XMLToSpreadingFireDOMs", "XMLToGameOfLifeDOMs.xml"};
	
	private static ParseXMLToDOM dataTransfer;

	public static void init(String simulationDotXMLStringName)	throws ParserConfigurationException, SAXException, IOException {
		
		// change to resource file later 
		Map<String, String> map = new HashMap<String, String>();
		map.put("schellingSegregation.xml", "XMLToSegregationDOMs");
		map.put("waTor.xml", "XMLToWaTorDOMs");
		map.put("spreadingFire.xml", "XMLToSpreadingFireDOMs");
		map.put("gameOfLife.xml", "XMLToGameOfLifeDOMs");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse((InitializeSimulation.class.getResourceAsStream(simulationDotXMLStringName)));
			Constructor<?> c = Class.forName(map.get(simulationDotXMLStringName)).getConstructor(Document.class); // add type reference 
			dataTransfer = (ParseXMLToDOM) c.newInstance(doc);
			dataTransfer.createDOMfromXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ParseXMLToDOM getDataTransfer() {
	    return dataTransfer;
	}
}
