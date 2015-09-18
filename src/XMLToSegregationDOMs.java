import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLToSegregationDOMs extends XMLToDOM {

	public XMLToSegregationDOMs(Document doc) {
		super(doc);
		// TODO Auto-generated constructor stub
	}

	@Override
	Simulation createSimulationWithXMLRules(Element simulationParameters) {
		Element gridProperties = (Element) simulationParameters.getElementsByTagName("rules").item(0);
		double satisfactionThresh = Double.parseDouble(gridProperties.getElementsByTagName("satisfactionThreshold").item(0).getTextContent());
		return new SchellingSimulation(satisfactionThresh);
	}

}
