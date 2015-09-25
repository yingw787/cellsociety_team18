//import java.util.List;
//
//public class AntSimulation extends SimulationWithAngle{
//    private static final int PHEROMONE_EVAPORATE = 1;
//    private static final int PHEROMONE_DIFFUSE = 1;
//    private static final int CROWDED_LEVEL = 10;
//    private static final int PHEROMONE_THRESH = 50;
//    public AntSimulation (GridOfCells cellSocietyGrid) {
//        super(cellSocietyGrid);
//    }
//
//    @Override
//    void processNeighbors (Cell currentCell, int x, int y) {
//        AntSpaceCell cell = (AntSpaceCell) currentCell;
//        for (Ant a: cell.getAnts()) {
//            if (a.hasFood()) {
//                goToNest(cell, a, x, y);
//            }
//            else {
//                goToFood(cell, a, x, y);
//            }
//        }
//    }
//
//    private void goToNest (AntSpaceCell currentCell, Ant a, int x, int y) {
//        if (currentCell.getMyCurrentState()==AntSpaceCell.FOOD) {
//            setOrientation(a,maxhomepheromones);
//        }
//        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
//        AntSpaceCell next = null;
//        for (Cell c: neighbors) {
//            AntSpaceCell cell = (AntSpaceCell) c;
//            if (next==null && cell.getHomePheromones()>0 && cell.getHomePheromones()>next.getHomePheromones()) { //TODO: check for same value of pheromones and randomness 
//                next=cell;
//            }
//        }
//        if (next==null) {
//            next = getfromallneighbors;
//        }
//        if (next!=null) {
//            a.leaveFoodPheromone(currentCell, PHEROMONE_THRESH);
//            setOrientation(a,next);
//            next.getAnts().add(a);
//            currentCell.getAnts().remove(a);
//            if (next.getMyCurrentState()==AntSpaceCell.HOME) {
//                //TODO:drop food item?
//                a.setHasFood(false);
//            }
//        }
//    }
//    private void goToFood (AntSpaceCell currentCell, Ant a, int x, int y) {
//        if (currentCell.getMyCurrentState()==AntSpaceCell.FOOD) {
//            setOrientation(a,maxfoodpheromones);
//        }
//        List<Cell> neighbors = getCellSocietyGrid().getNeighbors(x, y);
//        AntSpaceCell next = null;
//        for (Cell c: neighbors) {
//            AntSpaceCell cell = (AntSpaceCell) c;
//            if (next==null && cell.getFoodPheromones()>0 && cell.getFoodPheromones()>next.getFoodPheromones()) { //TODO: check for same value of pheromones and randomness 
//                next=cell;
//            }
//        }
//        if (next==null) {
//            next = getfromallneighbors;
//        }
//        if (next!=null) {
//            a.leaveHomePheromone(currentCell, PHEROMONE_THRESH);
//            setOrientation(a,next);
//            next.getAnts().add(a);
//            currentCell.getAnts().remove(a);
//            if (next.getMyCurrentState()==AntSpaceCell.FOOD) {
//                //TODO:drop food item?
//                a.setHasFood(true);
//            }
//        }
//    }
//
//    @Override
//    public String toString () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//}
