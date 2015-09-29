package parser;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import grid.GridOfCells;
import simulation.Simulation;


/**
 */
public class SimulationParser {

    private Element myRulesElement;
    private ResourceBundle myResourceBundle;
    private String[] mySimParameters;
    private String mySimulationClassName;

    /**
     * Constructor for SimulationParser.
     *
     * @param simulationElement Element
     * @param resourceBundle ResourceBundle
     * @param simulationClassName String
     */
    public SimulationParser (Element simulationElement,
                             ResourceBundle resourceBundle,
                             String simulationClassName) {
        myRulesElement = (Element) simulationElement.getElementsByTagName("parameters").item(0);
        myResourceBundle = resourceBundle;
        mySimulationClassName = simulationClassName;
        mySimParameters = myResourceBundle.getString(simulationClassName + "Parameters").split(",");
    }

    /**
     * Method createSimWithRules. Creates simulation based on type with rules from XML
     *
     * @param gridOfCells GridOfCells
     * @return Simulation
     */
    public Simulation createSimWithRules (GridOfCells gridOfCells) {
        String[] parameters = getParameters(mySimParameters);
        try {
            Constructor<?> c =
                    Class.forName(mySimulationClassName).getConstructor(GridOfCells.class,
                                                                        String[].class);
            return (Simulation) c.newInstance(gridOfCells, parameters);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Error! Not a simulation. " +
                                      "Simulation type doesnt match a known simulation. " +
                                      "Check properties file", mySimulationClassName);
        }
    }

    /**
     * Ensure that the order of the simulation properties in the @SimulationConstructors and
     * the @PropertiesFile are same
     * Method getParameters. Gets the simulation rules based on the rule name from the XML
     *
     * @param mySimParameters String[]
     * @return String[]
     */
    private String[] getParameters (String[] mySimParameters) {
        String[] parameters = new String[mySimParameters.length];
        for (int i = 0; i < parameters.length; i++) {
            String parameterName = mySimParameters[i];
            parameters[i] = myRulesElement.getElementsByTagName(parameterName).item(0)
                    .getTextContent();
        }
        return parameters;
    }

    /**
     * Method getSimulationClassName.
     *
     * @return String
     */
    public String getSimulationClassName () {
        return mySimulationClassName;
    }
}
