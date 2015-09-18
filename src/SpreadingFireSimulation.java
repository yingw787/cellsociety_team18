import java.util.ArrayList;

public class SpreadingFireSimulation extends Simulation{
    private double mySpreadRate;
    public SpreadingFireSimulation(double spreadRate) {
        mySpreadRate=spreadRate;
    }
    @Override
    void processNeighbors (Cell currentCell, int x, int y) {
        ArrayList<Cell> neighbors = getCellSocietyGrid().getAdjacentNeighbors(x,y);
        if (currentCell.getMyCurrentState()==TreeCell.HEALTHY) {
            for (Cell c : neighbors) {
                if (c.getMyCurrentState()==TreeCell.BURNING){
                    if (Math.random()<mySpreadRate){
                        currentCell.setMyFutureState(TreeCell.BURNING);
                    }
                    break;
                }
            }
        }
        else if (currentCell.getMyCurrentState()==TreeCell.BURNING) {
            currentCell.setMyFutureState(Cell.EMPTY);
        }
    }

}
