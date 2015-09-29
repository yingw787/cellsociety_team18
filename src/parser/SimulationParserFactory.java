package parser;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

import grid.GridOfCells;
import simulation.Simulation;

/**
 */
public class SimulationParserFactory {


    private Element mySimulationElement;
    private ResourceBundle myResourceBundle;
    private SimulationParser mySimulationParser;

    /**
     * Constructor for SimulationParserFactory.
     * @param simulationElement Element
     */
    public SimulationParserFactory (Element simulationElement) {
        this.mySimulationElement = simulationElement;
        myResourceBundle = ResourceBundle.getBundle(InitializeSimulation.DEFAULT_RESOURCE_PACKAGE + this.getClass().getSimpleName());
        String simulationName = mySimulationElement.getAttributes().getNamedItem("type").getNodeValue();
        String simulationClassName = myResourceBundle.getString(simulationName);
        mySimulationParser = new SimulationParser(mySimulationElement, myResourceBundle, simulationClassName);
    }


    /**
     * Method createSimulation. Creates simulation of the type specified in the XML
     * @param gridOfCells GridOfCells
     * @return Simulation
     */
    public Simulation createSimulation(GridOfCells gridOfCells){
        Simulation newSimulation = mySimulationParser.createSimWithRules(gridOfCells);
        return newSimulation;
    }


    /**
     * Method getSimulationParser.
     * @return SimulationParser
     */
    public SimulationParser getSimulationParser () {
        return mySimulationParser;
    }
}
