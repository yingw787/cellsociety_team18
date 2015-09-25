import java.util.Iterator;
import java.util.List;


public class FishSharkCell extends Cell {
    private int myCurrentSteps, myCurrentEnergy;

    public FishSharkCell (int x, int y) {
        super(new String[]{new Integer(Cell.EMPTY).toString(), new Integer(x).toString(), new Integer(y).toString()});
    }

    public FishSharkCell (String[] parameters) {
        super(parameters);
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



    public void decrementEnergy () {
        return;
    }
}
