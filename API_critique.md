

package gui;
public class Visualization extends GridPane { 
    public Visualization () 
    public Scene init (double visualizationWidth, double visualizationHeight, boolean grid) 
    public double getVisualizationHeight () (part of external API)
    public double getVisualizationWidth () (part of external API)
    public Shape getShape (Integer rowIndex, Integer columnIndex) (part of external API)
    public void drawCells (boolean grid) 
}
package gui;
public class VisualizationFactory { 
    public Visualization createVisualizationGrid (String polygonName, Simulation simulation) (part of public API)
    (public void addVisualizationToFactory (Visualization v)) (part of external API) 
    (public void setGridShape (GridOfCells g)) (part of external API)
}

package parser;
public class CellFactory { 
    public CellFactory (Element gridConfigurationTag, String simulationType) 
    public Cell getEmptyCell (String[] args) (part of external API)
    public List<Cell> getInitialCells () (part of external API)
    public int[] getGridBounds () (part of external API)
}
package parser;
public abstract class CellParser { 
    public CellParser (Element gridConfigurationTag,
    public Cell createEmptyCell (String[] args) 
    (protected Cell createCell (String[] properties)) (part of external API)
}

package parser;
public class SimulationParser { 
    public SimulationParser (Element simulationElement,
    public Simulation createSimWithRules (GridOfCells gridOfCells) (part of external API [string params])
    public String getSimulationClassName () 
}
package parser;
public class SimulationParserFactory { 
    public SimulationParserFactory (Element simulationElement) 
    public Simulation createSimulation (GridOfCells gridOfCells) (part of external API)
    public SimulationParser getSimulationParser () 
}

package simulation;
public abstract class Simulation { 
    public Simulation (GridOfCells cellSocietyGrid) 
    public void step () (external API)
    public void firstPass () (not part of API)
    public void secondPass () (not part of API)
    public void playAndLoop (Timeline timeline, KeyFrame action) (external API)
    public void checkNeighbors () (should be protected, part of internal API)
    public void updateCurrentStates () (should be protected, part of internal API)
    public GridOfCells getCellSocietyGrid () (external API)
    public static void print (GridOfCells grid) (external API)
}
package simulation;
public abstract class SimulationWithAngleAndPatch extends Simulation { 
    public SimulationWithAngleAndPatch (GridOfCells cellSocietyGrid, int sniffThreshold) 
    public List<Cell> processNeighborAngle (List<Cell> neighbors, Cell cell, double angle)  (should be protected, part of internal API)
    public void patchMovement (Cell cell, List<Cell> neighbors) (should be protected, part of internal API)
    public void patchDiffuse (List<Cell> neighbors, int i)  (should be protected, part of internal API)
    public void patchDecay (CellWithAngleAndPatch cCell, int i)  (should be protected, part of internal API)
    public Cell findMaxPatch (List<Cell> neighbors, CellWithAngleAndPatch cCell, int index)  (not part of API)
}

The simulation API mostly provides an interface to create instances of classes, which is the constructor and is used heavily by the parser. It also provides many get and set methods which is heavily used by the GUI. It also has a few timeline methods that are also called by the GUI. The abstract classes also have methods that are part of its internal API since they should be extended in order to add new features to the simulation.
