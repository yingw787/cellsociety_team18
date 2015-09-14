
public class CellSociety {
    private Simulation mySimulation;
    private GridOfCells myGrid;
    public void init() {
        mySimulation = xmlParser.parseSimulation();
        myGrid = xmlParser.parseGrid();
    }
}
