package parser;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

import grid.GridOfCells;
import simulation.Simulation;

public class SimulationParserFactory {


    private Element mySimulationElement;
    private ResourceBundle myResourceBundle;
    private SimulationParser mySimulationParser;

    public SimulationParserFactory (Element simulationElement) {
        this.mySimulationElement = simulationElement;
        myResourceBundle = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getSimpleName());
        String simulationName = mySimulationElement.getAttributes().getNamedItem("type").getNodeValue();
        String simulationClassName = myResourceBundle.getString(simulationName);
        mySimulationParser = new SimulationParser(mySimulationElement, myResourceBundle, simulationClassName);
    }


    public Simulation createSimulation(GridOfCells gridOfCells){
        Simulation newSimulation = mySimulationParser.createSimWithRules(gridOfCells);
        return newSimulation;
    }


    public SimulationParser getSimulationParser () {
        return mySimulationParser;
    }
}
