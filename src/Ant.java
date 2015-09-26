
public class Ant extends CellWithAngleAndPatch{
    private static final int LEAVE_FOOD = 10;
    private static final int LEAVE_HOME = 10;
    private boolean hasFood;
    public Ant (double angle) {
        super(0, 0, 0, angle);
        setHasFood(false);
        // TODO Auto-generated constructor stub
    }
    public void leaveFoodPheromone(AntSpaceCell cell, int threshold) {
        if (cell.getMyFuturePatch().get(1)<threshold){
            cell.getMyFuturePatch().set(1,cell.getMyFuturePatch().get(1)+LEAVE_FOOD);
        }
    }
    public void leaveHomePheromone(AntSpaceCell cell, int threshold) {
        if (cell.getMyFuturePatch().get(0)<threshold){
            cell.getMyFuturePatch().set(0,cell.getMyFuturePatch().get(0)+LEAVE_HOME);
        }
    }
    public boolean hasFood () {
        return hasFood;
    }
    public void setHasFood (boolean hasFood) {
        this.hasFood = hasFood;
    }
}
