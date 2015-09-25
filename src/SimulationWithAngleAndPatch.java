import java.util.ArrayList;
import java.util.List;

public abstract class SimulationWithAngleAndPatch extends Simulation{
    private static final int PATCH_DECAY = 4;
    private static final int PATCH_TRANSFER = 1;
    private int mySniffThreshold;
    public SimulationWithAngleAndPatch (GridOfCells cellSocietyGrid, int sniffThreshold) {
        super(cellSocietyGrid);
        mySniffThreshold=sniffThreshold;
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
    
    public void patchMovement (Cell cell, List<Cell> neighbors) {
        CellWithAngleAndPatch cCell = (CellWithAngleAndPatch) cell;
        for (int i = 0; i<cCell.getMyPatch().size(); i++) {
        if (cCell.getMyPatch().get(i)>0) {
            patchDecay(cCell, i);
            patchDiffuse(neighbors, i);
        }
        }
    }

    public void patchDiffuse (List<Cell> neighbors, int i) {
        for (Cell c: neighbors) {
            SlimeCell neighborC = (SlimeCell)c;
            neighborC.getMyFuturePatch().set(i, neighborC.getMyFuturePatch().get(i)+PATCH_TRANSFER);
        }
    }

    public void patchDecay (CellWithAngleAndPatch cCell, int i) {
        cCell.getMyFuturePatch().set(i,cCell.getMyFuturePatch().get(i)-PATCH_DECAY);
        if (cCell.getMyFuturePatch().get(i)<0) {
            cCell.getMyFuturePatch().set(i,0);
        }
    }
    public Cell findMaxPatch(List<Cell> neighbors, CellWithAngleAndPatch cCell, int index) {
        int maxPatchLevel=-1;
        Cell maxPatch = null;
        for (Cell cell: neighbors) {
            CellWithAngleAndPatch c=(CellWithAngleAndPatch)cell;
            if (c.getMyPatch().get(index)>maxPatchLevel && c.getMyPatch().get(index)>mySniffThreshold && c.getMyPatch().get(index)>0) {
                maxPatch = c;
                maxPatchLevel=c.getMyPatch().get(index);
            }
        }
        if (cCell!=null && cCell.getMyPatch().get(index)>maxPatchLevel && cCell.getMyPatch().get(index)>mySniffThreshold && cCell.getMyPatch().get(index)>0) {
            maxPatch = cCell;
            maxPatchLevel=cCell.getMyPatch().get(index);
        }
        return maxPatch;
    }
}
