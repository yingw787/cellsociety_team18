import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {




	public void main(String argv[]) {

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			File fXmlFile = new File(getClass().getClassLoader().getResourceAsStream("file.xml"));
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("staff");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {


			}
