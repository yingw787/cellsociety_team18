
import java.util.List;

public class SlimeSimulation extends SimulationWithAngle{

    private static final int CAMP_EMMISSION = 10;
    private static final int CAMP_DECAY = 4;
    private static final int CAMP_TRANSFER = 1;
    private int mySniffThreshold;
    private double myWiggleBias, myWiggleAngle, mySniffAngle;
    public SlimeSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid);
        mySniffThreshold=Integer.parseInt(parameters[0]);
        myWiggleBias=Double.parseDouble(parameters[1]);
        myWiggleAngle=Double.parseDouble(parameters[2]);
        mySniffAngle=Double.parseDouble(parameters[3]);
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        SlimeCell cCell = (SlimeCell) currentCell;
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        if (cCell.getMyCAmp()>0) {
            cCell.setMyFutureCAmp(cCell.getMyCAmp()-CAMP_DECAY);
            if (cCell.getMyFutureCAmp()<0) {
                cCell.setMyFutureCAmp(0);
            }
            for (Cell c: neighbors) {
                SlimeCell neighborC = (SlimeCell)c;
                neighborC.setMyFutureCAmp(neighborC.getMyFutureCAmp()+CAMP_TRANSFER);
            }
        }
        if (cCell.getMyCurrentState()==SlimeCell.occupied) {
            List<Cell> sniffNeighbors=processNeighborAngle(neighbors,x,y,mySniffAngle);
            int maxCAmpLevel=-1;
            SlimeCell maxCAmp = null;
            for (Cell cell: sniffNeighbors) {
                SlimeCell c=(SlimeCell)cell;
                if (c.getMyCAmp()>maxCAmpLevel && c.getMyCAmp()>mySniffThreshold && c.getMyCAmp()>0) {
                    maxCAmp = c;
                    maxCAmpLevel=c.getMyCAmp();
                }
            }
            if (cCell.getMyCAmp()>maxCAmpLevel && cCell.getMyCAmp()>mySniffThreshold && cCell.getMyCAmp()>0) {
                maxCAmp = cCell;
                maxCAmpLevel=cCell.getMyCAmp();
            }
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

    @Override
    public String toString () {
        // TODO Auto-generated method stub
        return null;
    }
}
