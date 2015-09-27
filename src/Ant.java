
public class Ant extends CellWithAngleAndPatch{
    private boolean hasFood;
    public Ant (String[] parameters) {
        super(parameters);
        setHasFood(false);
        // TODO Auto-generated constructor stub
    }
    public void leaveFoodPheromone(AntSpaceCell cell, int threshold, int amount) {
        if (cell.getMyFuturePatch().get(1)<threshold){
            cell.getMyFuturePatch().set(1,cell.getMyFuturePatch().get(1)+amount);
        }
    }
    public void leaveHomePheromone(AntSpaceCell cell, int threshold, int amount) {
        if (cell.getMyFuturePatch().get(0)<threshold){
            cell.getMyFuturePatch().set(0,cell.getMyFuturePatch().get(0)+amount);
        }
    }
    public boolean hasFood () {
        return hasFood;
    }
    public void setHasFood (boolean hasFood) {
        this.hasFood = hasFood;
    }
}
