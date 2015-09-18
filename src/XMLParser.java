import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {

	private final static String[] XMLFileNames = {"schellingSegregation.xml", "waTor.xml", "spreadingFire.xml", "gameOfLife.xml"};
	private final static String[] parserClassNames = {"XMLToSegregationDOMs", "XMLToWaTorDOMs", "XMLToSpreadingFireDOMs", "XMLToGameOfLifeDOMs.xml"};
	
	private static XMLToDOM dataTransfer;

	public static void main(String argv[])	throws ParserConfigurationException, SAXException, IOException {

		int sim = 1;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse((XMLParser.class.getResourceAsStream(XMLFileNames[sim])));
			Constructor c = Class.forName(parserClassNames[sim]).getConstructor(Document.class);
			dataTransfer = (XMLToDOM) c.newInstance(doc);
			dataTransfer.createDOMfromXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
