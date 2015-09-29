
import java.util.List;

public class SlimeSimulation extends SimulationWithAngleAndPatch{

    private int myCAmpEmmision;
    private double myWiggleBias, myWiggleAngle, mySniffAngle;
    public SlimeSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid, Integer.parseInt(parameters[2]));
        myWiggleBias=Double.parseDouble(parameters[0]);
        myWiggleAngle=Double.parseDouble(parameters[1]);
        mySniffAngle=Double.parseDouble(parameters[3]);
        myCAmpEmmision=Integer.parseInt(parameters[4]);
    }

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
                    cCell.setMyFutureCAmp(cCell.getMyFutureCAmp()+myCAmpEmmision);
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


    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                SlimeCell cell = (SlimeCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                cell.setCurrentState(cell.getFutureState());
                cell.setMyCAmp(cell.getMyFutureCAmp());
                cell.setMyFutureCAmp(cell.getMyFutureCAmp());
                cell.setAngle(cell.getFutureAngle());
                cell.setRefractory(cell.isFutureRefractory());
            }
        }
    }
}
