import java.util.ArrayList;
import java.util.List;

public class AntSpaceCell extends CellWithAngleAndPatch{
    public static final int HOME=1;
    public static final int FOOD=2;
    private List<Ant> ants;
    private List<Ant> futureAnts;
    public AntSpaceCell (String[] parameters) {
        super(parameters);
        getMyPatch().add(Integer.parseInt(parameters[this.PATCH_1_PARAMETER_INDEX]));
        getMyPatch().add(Integer.parseInt(parameters[this.PATCH_2_PARAMETER_INDEX]));
        getMyFuturePatch().add(Integer.parseInt(parameters[this.PATCH_1_PARAMETER_INDEX]));
        getMyFuturePatch().add(Integer.parseInt(parameters[this.PATCH_2_PARAMETER_INDEX]));
        System.out.println("QQQQQQQQQQQQQQQQq");
        initializeAnts(Integer.parseInt(parameters[this.NUM_ANT_PARAMETER_INDEX]));
        // TODO Auto-generated constructor stub
    }
    private void initializeAnts (int numAnts) {
        ants=new ArrayList<Ant>();
        futureAnts=new ArrayList<Ant>();
        for (int i=0; i<numAnts; i++) {
            Ant a = new Ant(new String[]{"0","0","0","0","0","0"});
            ants.add(a);
            futureAnts.add(a);
        }
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
