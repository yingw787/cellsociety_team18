import java.util.ArrayList;

public class SlimeSimulation extends Simulation{

    private static final int CAMP_EMMISSION = 10;
    private static final int CAMP_DECAY = 4;
    private static final int CAMP_TRANSFER = 1;
    private int mySniffThreshold;
    private double myWiggleBias, myWiggleAngle, mySniffAngle;
    public SlimeSimulation (GridOfCells cellSocietyGrid, double wiggleBias, double wiggleAngle, int sniffThreshold, double sniffAngle) {
        super(cellSocietyGrid);
        mySniffThreshold=sniffThreshold;
        myWiggleBias=wiggleBias;
        myWiggleAngle=wiggleAngle;
        mySniffAngle=sniffAngle;
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        SlimeCell cCell = (SlimeCell) currentCell;
        ArrayList<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
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
            ArrayList<Cell> sniffNeighbors=processNeighborAngle(neighbors,x,y,mySniffAngle);
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
                maxCAmp.setMyFutureAngle(cCell.getMyAngle());
                maxCAmp.setFutureRefractory(true);
            }
            else {
                ArrayList<Cell> moveNeighbors=processNeighborAngle(neighbors,x,y,myWiggleAngle);
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

    private ArrayList<Cell> processNeighborAngle (ArrayList<Cell> neighbors, int x, int y, double angle) {
        ArrayList<Cell> neighborsInRange = new ArrayList<Cell>();
        for (Cell c: neighbors) {
            if (c.getMyCurrentState()==Cell.EMPTY &&c.getMyFutureState()==Cell.EMPTY) {
                neighborsInRange.add(c);
            }
        }

        //        SlimeCell s = (SlimeCell)getCellSocietyGrid().getMyCells().get(y).get(x);
        //        int facexmin=(int) Math.round(Math.cos((s.getMyAngle()-angle/2)*Math.PI/180));
        //        int faceymin=(int) Math.round(Math.sin((s.getMyAngle()-angle/2)*Math.PI/180));
        //        int facexmax=(int) Math.round(Math.cos((s.getMyAngle()+angle/2)*Math.PI/180));
        //        int faceymax=(int) Math.round(Math.sin((s.getMyAngle()+angle/2)*Math.PI/180));
        //        System.out.println(facexmax);
        //        for (Cell c: neighbors) {
        //            if (((facexmax<=c.getMyXCoordinate() && c.getMyXCoordinate()<=facexmin)||
        //                    (facexmax>=c.getMyXCoordinate() && c.getMyXCoordinate()>=facexmin))&&
        //                    ((faceymax<=c.getMyYCoordinate() && c.getMyYCoordinate()<=faceymin)||
        //                            (faceymax>=c.getMyYCoordinate() && c.getMyYCoordinate()>=faceymin))){
        //                neighborsInRange.add(c);
        //            }
        //        }
        return neighborsInRange;
    }
    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                SlimeCell cell = (SlimeCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                cell.setMyCurrentState(cell.getMyFutureState());
                cell.setMyCAmp(cell.getMyFutureCAmp());
                cell.setMyFutureCAmp(cell.getMyFutureCAmp());
                cell.setMyAngle(cell.getMyFutureAngle());
                cell.setRefractory(cell.isFutureRefractory());
            }
        }
    }
}
