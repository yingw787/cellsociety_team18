import java.util.ResourceBundle;
import org.w3c.dom.Element;

public class SimulationParserFactory {

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

    private Element mySimulationElement;
    private ResourceBundle myResourceBundle;
    private SimulationParser mySimulationParser;

    public SimulationParserFactory (Element simulationElement) {
        this.mySimulationElement = simulationElement;
        myResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Simulation");
    }


    public Simulation createSimulationParser(){

        String simulationName = mySimulationElement.getAttributes().getNamedItem("type").getNodeValue();

        String simulationClassName = myResourceBundle.getString(simulationName);
        mySimulationParser = new SimulationParser(mySimulationElement, myResourceBundle, simulationClassName);
        Simulation newSimulation = mySimulationParser.createSimWithRules();
        return newSimulation;

//        String parserName = myResourceBundle.getString(simulationClassName+"ParameterParser");
//        try {
//            Constructor<?> c = Class.forName(parserName).getConstructor(Element.class, ResourceBundle.class, String.class);
//            System.out.println("contructor: "+ c.getName());
//            mySimulationParser = (SimulationParser) c.newInstance(mySimulationElement, myResourceBundle, simulationClassName);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            throw new ParserException("Error! Not a simulation. " +
//                    "\nSimulation type doesnt match a known parser. " +
//                    "Check properties file", simulationName);
//        }
        
    }


}
