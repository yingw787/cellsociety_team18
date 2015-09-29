import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class SimulationParser {

    private Element myRulesElement;
    private ResourceBundle myResourceBundle;
    private String[] mySimParameters;
    private String mySimulationClassName;
    

    public SimulationParser (Element simulationElement, ResourceBundle resourceBundle, String simulationClassName) {
        this.myRulesElement = (Element)simulationElement.getElementsByTagName("parameters").item(0);
        this.myResourceBundle = resourceBundle;
        this.mySimulationClassName = simulationClassName;
        this.mySimParameters = myResourceBundle.getString(simulationClassName+"Parameters").split(",");
    }
    
    public Simulation createSimWithRules (GridOfCells gridOfCells) {
        String[] parameters = getParameters(mySimParameters);
        try {
            Constructor<?> c = Class.forName(mySimulationClassName).getConstructor(GridOfCells.class, String[].class);
            return (Simulation) c.newInstance(gridOfCells, parameters);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ParserException("Error! Not a simulation. " +
                    "Simulation type doesnt match a known simulation. " +
                    "Check properties file", mySimulationClassName);
        }
    }
    
    /**
     * Ensure that the order of the simulation properties 
     * in the  @SimulationConstructors and the @PropertiesFile are same
     */
    private String[] getParameters (String[] mySimParameters) {
        String[] parameters = new String[mySimParameters.length];
        for(int i=0; i<parameters.length; i++){
            String parameterName = mySimParameters[i];
            System.out.println("parameter: "+parameterName);
            parameters[i] = myRulesElement.getElementsByTagName(parameterName).item(0)
                                     .getTextContent();
        }
        return parameters;
    }
    
    public String getMySimulationClassName () {
        return mySimulationClassName;
    }
}
