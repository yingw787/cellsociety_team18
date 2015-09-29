package simulation;

import java.util.ArrayList;
import java.util.List;
import cell.Cell;
import cell.CellWithAngleAndPatch;
import grid.GridOfCells;


/**
 * Extends the simulation class to provide angles and patches as will as accessing and modifying
 * them.
 */
public abstract class SimulationWithAngleAndPatch extends Simulation {
    private static final int PATCH_DECAY = 4;
    private static final int PATCH_TRANSFER = 1;
    private int mySniffThreshold;

    /**
     * Instantiates a new simulation with angle and patch.
     *
     * @param cellSocietyGrid the cell society grid
     * @param sniffThreshold the sniff threshold
     */
    public SimulationWithAngleAndPatch (GridOfCells cellSocietyGrid, int sniffThreshold) {
        super(cellSocietyGrid);
        mySniffThreshold = sniffThreshold;
    }

    /**
     * Process neighbor angle.
     *
     * @param neighbors the neighbors
     * @param cell the cell
     * @param angle the angle
     * @return the list
     */
    public List<Cell> processNeighborAngle (List<Cell> neighbors, Cell cell, double angle) {
        List<Cell> neighborsInRange = new ArrayList<Cell>();
        for (Cell c : neighbors) {
            if (c.getCurrentState() == Cell.EMPTY && c.getFutureState() == Cell.EMPTY) {
                neighborsInRange.add(c);

            }
        }
        //// Buggy beta code that checks if angle is within the range of a cell's arbitrary
        //// orientation and angle
        //
        // SlimeCell s = (SlimeCell)getCellSocietyGrid().getMyCells().get(y).get(x);
        // int facexmin=(int) Math.round(Math.cos((s.getMyAngle()-angle/2)*Math.PI/180));
        // int faceymin=(int) Math.round(Math.sin((s.getMyAngle()-angle/2)*Math.PI/180));
        // int facexmax=(int) Math.round(Math.cos((s.getMyAngle()+angle/2)*Math.PI/180));
        // int faceymax=(int) Math.round(Math.sin((s.getMyAngle()+angle/2)*Math.PI/180));
        // System.out.println(facexmax);
        // for (Cell c: neighbors) {
        // if (((facexmax<=c.getMyXCoordinate() && c.getMyXCoordinate()<=facexmin)||
        // (facexmax>=c.getMyXCoordinate() && c.getMyXCoordinate()>=facexmin))&&
        // ((faceymax<=c.getMyYCoordinate() && c.getMyYCoordinate()<=faceymin)||
        // (faceymax>=c.getMyYCoordinate() && c.getMyYCoordinate()>=faceymin))){
        // neighborsInRange.add(c);
        // }
        // }
        return neighborsInRange;
    }

    /**
     * Patch movement.
     *
     * @param cell the cell
     * @param neighbors the neighbors
     */
    public void patchMovement (Cell cell, List<Cell> neighbors) {
        CellWithAngleAndPatch cCell = (CellWithAngleAndPatch) cell;
        for (int i = 0; i < cCell.getPatch().size(); i++) {
            if (cCell.getPatch().get(i) > 0) {
                patchDecay(cCell, i);
                patchDiffuse(neighbors, i);
            }
        }
    }

    /**
     * Patch diffuse.
     *
     * @param neighbors the neighbors
     * @param i the i
     */
    public void patchDiffuse (List<Cell> neighbors, int i) {
        for (Cell c : neighbors) {
            CellWithAngleAndPatch neighborC = (CellWithAngleAndPatch) c;
            neighborC.getFuturePatch().set(i, neighborC.getFuturePatch().get(i) + PATCH_TRANSFER);
        }
    }

    /**
     * Patch decay.
     *
     * @param cCell the c cell
     * @param i the i
     */
    public void patchDecay (CellWithAngleAndPatch cCell, int i) {
        cCell.getFuturePatch().set(i, cCell.getFuturePatch().get(i) - PATCH_DECAY);
        if (cCell.getFuturePatch().get(i) < 0) {
            cCell.getFuturePatch().set(i, 0);
        }
    }

    /**
     * Find the cell with the maximum patch value.
     *
     * @param neighbors the neighbors
     * @param cCell the c cell
     * @param index the index
     * @return the cell
     */
    public Cell findMaxPatch (List<Cell> neighbors, CellWithAngleAndPatch cCell, int index) {
        int maxPatchLevel = -1;
        Cell maxPatch = null;
        for (Cell cell : neighbors) {
            CellWithAngleAndPatch c = (CellWithAngleAndPatch) cell;
            if (c.getPatch().get(index) > maxPatchLevel &&
                c.getPatch().get(index) > mySniffThreshold && c.getPatch().get(index) > 0) {
                maxPatch = c;
                maxPatchLevel = c.getPatch().get(index);
            }
        }
        if (cCell != null && cCell.getPatch().get(index) > maxPatchLevel &&
            cCell.getPatch().get(index) > mySniffThreshold && cCell.getPatch().get(index) > 0) {
            maxPatch = cCell;
            maxPatchLevel = cCell.getPatch().get(index);
        }
        return maxPatch;
    }
}
