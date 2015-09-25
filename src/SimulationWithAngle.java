import java.util.ArrayList;
import java.util.List;

public abstract class SimulationWithAngle extends Simulation{

    public SimulationWithAngle (GridOfCells cellSocietyGrid) {
        super(cellSocietyGrid);
    }
    public List<Cell> processNeighborAngle (List<Cell> neighbors, int x, int y, double angle) {
        List<Cell> neighborsInRange = new ArrayList<Cell>();
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
}
