import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class SimulationParserFactory {


    private Element mySimulationElement;
    private ResourceBundle myResourceBundle;
    private SimulationParser mySimulationParser;

    public SimulationParserFactory (Element simulationElement) {
        this.mySimulationElement = simulationElement;
        myResourceBundle = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getName());
        String simulationName = mySimulationElement.getAttributes().getNamedItem("type").getNodeValue();
        String simulationClassName = myResourceBundle.getString(simulationName);
        mySimulationParser = new SimulationParser(mySimulationElement, myResourceBundle, simulationClassName);
    }


    public Simulation createSimulation(GridOfCells gridOfCells){
        Simulation newSimulation = mySimulationParser.createSimWithRules(gridOfCells);
        return newSimulation;
    }


    public SimulationParser getMySimulationParser () {
        return mySimulationParser;
    }
}
