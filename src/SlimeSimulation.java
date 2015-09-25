import java.util.ArrayList;
import java.util.List;

public class SlimeSimulation extends SimulationWithAngleAndPatch{

    private static final int CAMP_EMMISSION = 10;
    private double myWiggleBias, myWiggleAngle, mySniffAngle;
    public SlimeSimulation (GridOfCells cellSocietyGrid, double wiggleBias, double wiggleAngle, int sniffThreshold, double sniffAngle) {
        super(cellSocietyGrid, sniffThreshold);
        myWiggleBias=wiggleBias;
        myWiggleAngle=wiggleAngle;
        mySniffAngle=sniffAngle;
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        SlimeCell cCell = (SlimeCell) currentCell;
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        patchMovement(cCell, neighbors);
        if (cCell.getMyCurrentState()==SlimeCell.occupied) {
            List<Cell> sniffNeighbors=processNeighborAngle(neighbors,x,y,mySniffAngle);
            SlimeCell maxCAmp = (SlimeCell)findMaxPatch(sniffNeighbors, cCell, 0);
            if (maxCAmp!=null) {
                cCell.setMyFutureState(Cell.EMPTY);
                if (!cCell.isRefractory()) {
                    cCell.setMyFutureCAmp(cCell.getMyFutureCAmp()+CAMP_EMMISSION);
                }
                maxCAmp.setMyFutureState(SlimeCell.occupied);
                maxCAmp.setFutureAngle(cCell.getAngle());
                maxCAmp.setFutureRefractory(true);
            }
            else {
                List<Cell> moveNeighbors=processNeighborAngle(neighbors,x,y,myWiggleAngle);
                try {
                    Cell random = moveNeighbors.get((int)(Math.random()*moveNeighbors.size()));
                    currentCell.setMyFutureState(Cell.EMPTY);
                    random.setMyFutureState(SlimeCell.occupied);
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
                cell.setMyCurrentState(cell.getMyFutureState());
                cell.setMyCAmp(cell.getMyFutureCAmp());
                cell.setMyFutureCAmp(cell.getMyFutureCAmp());
                cell.setAngle(cell.getFutureAngle());
                cell.setRefractory(cell.isFutureRefractory());
            }
        }
    }
}
