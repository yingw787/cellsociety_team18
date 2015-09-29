
import java.util.List;

/**
 * Contains the rules and variables for the slime simulation
 */
public class SlimeSimulation extends SimulationWithAngleAndPatch{

    private int myCAmpEmmision;
    private double myWiggleBias, myWiggleAngle, mySniffAngle;
    
    /**
     * Instantiates a new slime simulation.
     *
     * @param cellSocietyGrid the cell society grid
     * @param parameters the parameters
     */
    public SlimeSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid, Integer.parseInt(parameters[2]));
        myWiggleBias=Double.parseDouble(parameters[0]);
        myWiggleAngle=Double.parseDouble(parameters[1]);
        mySniffAngle=Double.parseDouble(parameters[3]);
        myCAmpEmmision=Integer.parseInt(parameters[4]);
    }

    /* (non-Javadoc)
     * @see Simulation#processNeighbors(Cell, int, int)
     */
    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        SlimeCell cCell = (SlimeCell) currentCell;
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        patchMovement(cCell, neighbors);
        if (cCell.getCurrentState()==SlimeCell.occupied) {
            List<Cell> sniffNeighbors=processNeighborAngle(neighbors,cCell,mySniffAngle);
            SlimeCell maxCAmp = (SlimeCell)findMaxPatch(sniffNeighbors, cCell, 0);
            if (maxCAmp!=null) {
                cCell.setFutureState(Cell.EMPTY);
                if (!cCell.isRefractory()) {
                    cCell.setFutureCAmp(cCell.getFutureCAmp()+myCAmpEmmision);
                }
                maxCAmp.setFutureState(SlimeCell.occupied);
                maxCAmp.setFutureAngle(cCell.getAngle());
                maxCAmp.setFutureRefractory(true);
            }
            else {
                List<Cell> moveNeighbors=processNeighborAngle(neighbors,cCell,myWiggleAngle);
                try {
                    Cell random = moveNeighbors.get((int)(Math.random()*moveNeighbors.size()));
                    currentCell.setFutureState(Cell.EMPTY);
                    random.setFutureState(SlimeCell.occupied);
                }
                catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }


    /* (non-Javadoc)
     * @see Simulation#updateCurrentStates()
     */
    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getCells().get(0).size(); x++) {
                SlimeCell cell = (SlimeCell) getCellSocietyGrid().getCells().get(y).get(x);
                cell.setCurrentState(cell.getFutureState());
                cell.setCAmp(cell.getFutureCAmp());
                cell.setFutureCAmp(cell.getFutureCAmp());
                cell.setAngle(cell.getFutureAngle());
                cell.setRefractory(cell.isFutureRefractory());
            }
        }
    }
}
