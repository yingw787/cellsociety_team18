
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FishSharkCell extends Cell {
    private int myCurrentSteps, myCurrentEnergy;
    private int myReproductionSteps, myGainEnergy;

    public FishSharkCell (int x, int y) {
        super(Cell.EMPTY, x, y);
    }

    public FishSharkCell (int state, int x, int y) {
        super(state, x, y);
    }

    @Override
    public Cell getSwapNeighbor (List<Cell> neighbors) {
        return null;
    }

    protected Cell getSwapNeighborHelper (List<Cell> neighbors) {
        Iterator<Cell> iter = neighbors.iterator();
        while (iter.hasNext()) {
            Cell c = iter.next();
            if (c.getMyFutureState() != Cell.EMPTY) {
                iter.remove();
            }
        }
        if (neighbors.size() != 0) {
            Cell swapee = neighbors.get((int) (Math.random() * neighbors.size()));
            return swapee;
        }
        return null;
    }

    public int getMyCurrentSteps () {
        return myCurrentSteps;
    }

    public void setMyCurrentSteps (int myCurrentSteps) {
        this.myCurrentSteps = myCurrentSteps;
    }

    public int getMyCurrentEnergy () {
        return myCurrentEnergy;
    }

    public void setMyCurrentEnergy (int myCurrentEnergy) {
        this.myCurrentEnergy = myCurrentEnergy;
    }

    public int getMyReproductionSteps () {
        return myReproductionSteps;
    }

    public void setMyReproductionSteps (int myReproductionSteps) {
        this.myReproductionSteps = myReproductionSteps;
    }

    public int getMyGainEnergy () {
        return myGainEnergy;
    }

    public void setMyGainEnergy (int myGainEnergy) {
        this.myGainEnergy = myGainEnergy;
    }

    public void decrementEnergy () {
        return;
    }
}
