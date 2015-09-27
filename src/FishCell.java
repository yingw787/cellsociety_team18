import java.util.List;


public class FishCell extends FishSharkCell {
    public static final int FISH = 1;

    public FishCell (int x, int y) {
        super(new String[]{new Integer(FISH).toString(), new Integer(x).toString(), new Integer(y).toString()});
        setMyCurrentSteps(0);
        setMyCurrentEnergy(1);
    }
    public FishCell (String parameters[]) {
        super(parameters);
        setMyCurrentSteps(0);
        setMyCurrentEnergy(1);
    }

    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return (getSwapNeighborHelper(neighbors));
    }

}
