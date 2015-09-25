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
    
    public Simulation createSimWithRules () {
        String[] parameters = getParameters(this.getMySimParameters());
        try {
            Constructor<?> c = Class.forName(this.getMySimulationClassName()).getConstructor(GridOfCells.class, String[].class);
            return (Simulation) c.newInstance(null, parameters);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ParserException("Error! Not a simulation. " +
                    "Simulation type doesnt match a known simulation. " +
                    "Check properties file", getMySimulationClassName());
        }
    }
    
    /**
     * Ensure that the order of the properties in the Simulation constructors and the Properties file are same
     */
    private String[] getParameters (String[] mySimParameters) {
        String[] parameters = new String[mySimParameters.length];
        for(int i=0; i<parameters.length; i++){
            String parameterName = this.getMySimParameters()[i];
            System.out.println("parameter: "+parameterName);
            parameters[i] = 
                    this.getMyRulesElement().getElementsByTagName(parameterName).item(0)
                                     .getTextContent();
        }
        return parameters;
    }
    
    protected String[] getMySimParameters () {
        return mySimParameters;
    }
    
    public String getMySimulationClassName () {
        return mySimulationClassName;
    }

    protected Element getMyRulesElement () {
        return myRulesElement;
    }

    protected ResourceBundle getMyResourceBundle () {
        return myResourceBundle;
    }

}
