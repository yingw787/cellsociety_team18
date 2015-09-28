
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class AntSimulation extends SimulationWithAngleAndPatch{
    private int myCrowdedLevel;
    private int myPheromoneThreshold;
    private int myLeaveFood;
    private int myLeaveHome;
    public AntSimulation (GridOfCells cellSocietyGrid, String[] parameters) {
        super(cellSocietyGrid,Integer.MAX_VALUE);
        myCrowdedLevel=Integer.parseInt(parameters[0]);
        myPheromoneThreshold=Integer.parseInt(parameters[1]);
        myLeaveFood=Integer.parseInt(parameters[2]);
        myLeaveHome=Integer.parseInt(parameters[3]);
    }

    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        AntSpaceCell cell = (AntSpaceCell) currentCell;
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        patchMovement(currentCell, neighbors);
        for (Ant a: cell.getCurrentAnts()) {
            if (a.hasFood()) {
                goToNest(cell, a, x, y);
            }
            else {
                goToFood(cell, a, x, y);
            }
        }
    }

    private void goToNest (AntSpaceCell currentCell, Ant a, int x, int y) {
        AntSpaceCell next = getMovement(currentCell, a, x, y, AntSpaceCell.HOMEINDEX, AntSpaceCell.FOOD);
        if (next!=null) {
            a.leaveFoodPheromone(currentCell, myPheromoneThreshold,myLeaveFood);
            a.setOrientation(next, currentCell.getMyXCoordinate(), currentCell.getMyYCoordinate());
            next.getFutureAnts().add(a);
            currentCell.getFutureAnts().remove(a);
            if (next.getMyCurrentState()==AntSpaceCell.HOME) {
                //TODO:drop food item?
                a.setHasFood(false);
            }
        }
    }
    private void goToFood (AntSpaceCell currentCell, Ant a, int x, int y) {
        AntSpaceCell next = getMovement(currentCell, a, x, y, AntSpaceCell.FOODINDEX, AntSpaceCell.HOME);
        if (next!=null) {
            a.leaveHomePheromone(currentCell, myPheromoneThreshold, myLeaveHome);
            a.setOrientation(next, currentCell.getMyXCoordinate(), currentCell.getMyYCoordinate());
            next.getFutureAnts().add(a);
            currentCell.getFutureAnts().remove(a);
            if (next.getMyCurrentState()==AntSpaceCell.FOOD) {
                a.setHasFood(true);
            }
        }
    }
    public AntSpaceCell getMovement(AntSpaceCell currentCell, Ant a, int x, int y, int patchIndex, int antSpaceState) {
        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
        List<Cell> forwardNeighbors = processNeighborAngle(neighbors, a, 90);
        Cell maxHomePheromones = findMaxPatch(neighbors,null,patchIndex);
        if (currentCell.getMyCurrentState()==antSpaceState) {
            if (maxHomePheromones!=null) {
                a.setOrientation(maxHomePheromones, 
                                 currentCell.getMyXCoordinate(),
                                 currentCell.getMyYCoordinate());
            }
        }
        AntSpaceCell next = (AntSpaceCell)findMaxPatch(forwardNeighbors,null,patchIndex);
        if (next==null) {
            next = (AntSpaceCell)maxHomePheromones;
        }
        if (next==null) {
            next = (AntSpaceCell)neighbors.get((int)(neighbors.size()*Math.random()));
        }
        return next;
    }
    @Override
    public List<Cell> processNeighborAngle (List<Cell> neighbors, Cell cCell, double angle) {
        CellWithAngleAndPatch cell = (CellWithAngleAndPatch) cCell;
        List<Cell> neighborsInRange = new ArrayList<Cell>();
        List<Pair<Integer,Integer>> directions = new ArrayList<Pair<Integer,Integer>>();
        for (int i=-1; i<=1; i++) {
            int x = (int)(Math.round(Math.cos((cell.getAngle()+i*45)*Math.PI/180)));
            int y = (int)(Math.round(Math.sin((cell.getAngle()+i*45)*Math.PI/180)));
            directions.add(new Pair<Integer,Integer>(x,y));
        }
        for (Cell neighborCell: neighbors) {
            AntSpaceCell c = (AntSpaceCell) neighborCell;
            if (c.getFutureAnts().size()<myCrowdedLevel) {
                for (Pair p: directions) {
                    if (c.getMyXCoordinate()==(cCell.getMyXCoordinate()+(int)p.getKey()) && c.getMyYCoordinate()==(cCell.getMyYCoordinate()+(int)p.getValue())) {
                        neighborsInRange.add(c);
                    }
                }
            }
        }
        return neighborsInRange;
    }
    @Override
    public void updateCurrentStates () {
        for (int y = 0; y < getCellSocietyGrid().getMyCells().size(); y++) {
            for (int x = 0; x < getCellSocietyGrid().getMyCells().get(0).size(); x++) {
                AntSpaceCell cell = (AntSpaceCell) getCellSocietyGrid().getMyCells().get(y).get(x);
                cell.setMyCurrentState(cell.getMyFutureState());
                cell.setPheromones(cell.getFuturePheromones());
                cell.setFuturePheromones(new ArrayList<Integer>(cell.getFuturePheromones()));
                cell.setCurrentAnts(cell.getFutureAnts());
                cell.setFutureAnts(new ArrayList<Ant>(cell.getFutureAnts()));
            }
        }
    }
}