import org.w3c.dom.Element;

public class SimulationParserFactory {
    
    private String simulationType;
    private Element simulation;

    public SimulationParserFactory (String simulationType, Element simulation) {
        this.simulation = simulation;
        this.simulationType = simulationType;
    }

    
    public SimulationParser createSimulationParser(){
        
        
        return null;
    }
    
    
}
