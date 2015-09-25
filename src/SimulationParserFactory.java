import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class SimulationParserFactory {


    private Element mySimulationElement;
    private ResourceBundle myResourceBundle;
    private SimulationParser mySimulationParser;

    public SimulationParserFactory (Element simulationElement) {
        this.mySimulationElement = simulationElement;
        myResourceBundle = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getName());
    }


    public Simulation createSimulationParser(){

        String simulationName = mySimulationElement.getAttributes().getNamedItem("type").getNodeValue();

        String simulationClassName = myResourceBundle.getString(simulationName);
        mySimulationParser = new SimulationParser(mySimulationElement, myResourceBundle, simulationClassName);
        Simulation newSimulation = mySimulationParser.createSimWithRules();
        return newSimulation;
        
    }
}
