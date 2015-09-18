import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {

	private final static String[] XMLFileNames = {"schellingSegregation.xml", "waTor.xml", "spreadingFire.xml", "gameOfLife.xml"};
	private static XMLToDOM dataTransfer;

	public static void main(String argv[])	throws ParserConfigurationException, SAXException, IOException {

		int simulation = 1;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse((XMLParser.class.getResourceAsStream(XMLFileNames[simulation])));
//			String XMLSimulationName = doc.getDocumentElement().getNodeName();
			
//			return Class.forName(validatorClass).newInstance();
			dataTransfer = new XMLToWaTorDOMs(doc);
			dataTransfer.createDOMfromXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



//			NodeList nList = doc.getElementsByTagName("staff");
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//
//				Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					System.out.println("Staff id : " + eElement.getAttribute("id"));
//					System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//				}
//			}