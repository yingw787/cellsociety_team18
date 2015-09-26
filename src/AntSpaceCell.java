import java.util.ArrayList;
import java.util.List;

public class AntSpaceCell extends CellWithAngleAndPatch{
    public static final int HOME=1;
    public static final int FOOD=2;
    private int homePheromones=0;
    private int foodPheromones=0;
    private List<Ant> ants;
    private List<Ant> futureAnts;
    public AntSpaceCell (int state, int xCoordinate, int yCoordinate) {
        super(state, xCoordinate, yCoordinate, 0);
        getMyPatch().add(homePheromones);
        getMyPatch().add(foodPheromones);
        getMyFuturePatch().add(homePheromones);
        getMyFuturePatch().add(foodPheromones);
        ants=new ArrayList<Ant>();
        futureAnts=new ArrayList<Ant>();
        // TODO Auto-generated constructor stub
    }
    public List<Integer> getPheromones () {
        return getMyPatch();
    }
    public List<Integer> getFuturePheromones () {
        return getMyFuturePatch();
    }
    public void setPheromones (List<Integer> pheromones) {
        setMyPatch(pheromones);
    }
    public void setFuturePheromones (List<Integer> pheromones) {
        setMyFuturePatch(pheromones);
    }

    public List<Ant> getCurrentAnts () {
        return ants;
    }
    public List<Ant> getFutureAnts () {
        return futureAnts;
    }
    public void addFutureAnt (Ant ant) {
        futureAnts.add(ant);
    }
    public void deleteFutureAnt (Ant ant) {
        try {
            futureAnts.remove(ant);
        }
        catch (Exception e) {
        }
    }
    public void setCurrentAnts (List<Ant> list) {
        ants = list;
    }
    public void setFutureAnts (List<Ant> list) {
        ants = list;
    }
}
