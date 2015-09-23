
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XMLToGameOfLifeDOMs extends ParseXMLToDOM {

    public XMLToGameOfLifeDOMs (Document doc) {
        super(doc);
    }

    @Override
    Simulation createSimulationWithXMLRules (Element simulationParameters,
                                             GridOfCells gridOfCells) {
        Element rules = (Element) simulationParameters.getElementsByTagName("rules").item(0);
        int minNeighbors =
                Integer.parseInt(rules.getElementsByTagName("minNeighborsForSurvival").item(0)
                        .getTextContent());
        int maxNeighbors =
                Integer.parseInt(rules.getElementsByTagName("maxNeighborsForSurvival").item(0)
                        .getTextContent());
        int repNeighbors =
                Integer.parseInt(rules.getElementsByTagName("reproductionNeighbors").item(0)
                        .getTextContent());
        return new GameOfLifeSimulation(gridOfCells, minNeighbors, maxNeighbors, repNeighbors);
    }

    @Override
    Cell createCell (Element cell) {
        int x =
                Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0))
                        .getAttributes().getNamedItem("x").getNodeValue());
        int y =
                Integer.parseInt(((Element) cell.getElementsByTagName("location").item(0))
                        .getAttributes().getNamedItem("y").getNodeValue());
        int state =
                Integer.parseInt(((Element) cell.getElementsByTagName("state").item(0))
                        .getTextContent());
        		
        return new GameOfLifeCell(state, x - 1, y - 1);
    }

    @Override
    public Cell createEmptyCell (int x, int y) {
        return new GameOfLifeCell(Cell.EMPTY, x, y);
    }

}
