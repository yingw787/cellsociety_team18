
import java.util.ArrayList;
import java.util.List;


public class FishCell extends FishSharkCell {
    public static final int FISH = 1;

    public FishCell (int x, int y, int reproductionSteps) {
        super(FISH, x, y);
        setMyCurrentSteps(0);
        setMyReproductionSteps(reproductionSteps);
        setMyCurrentEnergy(1);
        setMyGainEnergy(1);
    }

    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return (getSwapNeighborHelper(neighbors));
    }

}
