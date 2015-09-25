
public class Ant extends CellWithAngleAndPatch{
    private static final int LEAVE_FOOD = 10;
    private static final int LEAVE_HOME = 10;
    private boolean hasFood;
    public Ant (int state, int xCoordinate, int yCoordinate, double angle) {
        super(state, xCoordinate, yCoordinate, angle);
        setHasFood(false);
        // TODO Auto-generated constructor stub
    }
    public void leaveFoodPheromone(AntSpaceCell cell, int threshold) {
        if (cell.getFutureFoodPheromones()<threshold){
            cell.setFutureFoodPheromones(cell.getFutureFoodPheromones()+LEAVE_FOOD);
        }
    }
    public void leaveHomePheromone(AntSpaceCell cell, int threshold) {
        if (cell.getFutureHomePheromones()<threshold){
            cell.setFutureHomePheromones(cell.getFutureHomePheromones()+LEAVE_HOME);
        }
    }
    public boolean hasFood () {
        return hasFood;
    }
    public void setHasFood (boolean hasFood) {
        this.hasFood = hasFood;
    }
}
