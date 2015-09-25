import java.util.ArrayList;
import java.util.List;

public class AntSpaceCell extends Cell{
    public static final int HOME=1;
    public static final int FOOD=2;
    private int homePheromones, foodPheromones, futureHomePheromones, futureFoodPheromones;
    private List<Ant> ants;
    public AntSpaceCell (int state, int xCoordinate, int yCoordinate) {
        super(state, xCoordinate, yCoordinate);
        setHomePheromones(0);
        setFoodPheromones(0);
        ants=new ArrayList<Ant>();
        // TODO Auto-generated constructor stub
    }
    public int getHomePheromones () {
        return homePheromones;
    }
    public void setHomePheromones (int homePheromones) {
        this.homePheromones = homePheromones;
    }
    public int getFoodPheromones () {
        return foodPheromones;
    }
    public void setFoodPheromones (int foodPheromones) {
        this.foodPheromones = foodPheromones;
    }
    public int getFutureHomePheromones () {
        return futureHomePheromones;
    }
    public void setFutureHomePheromones (int futureHomePheromones) {
        this.futureHomePheromones = futureHomePheromones;
    }
    public int getFutureFoodPheromones () {
        return futureFoodPheromones;
    }
    public void setFutureFoodPheromones (int futureFoodPheromones) {
        this.futureFoodPheromones = futureFoodPheromones;
    }
    public List<Ant> getAnts () {
        return ants;
    }
    public void setAnts (List<Ant> ants) {
        this.ants = ants;
    }

}
