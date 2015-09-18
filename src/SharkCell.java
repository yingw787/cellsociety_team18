import java.util.ArrayList;

public class SharkCell extends FishSharkCell{
    public static final int SHARK = 2;
    public SharkCell (int x, int y, int reproductionSteps, int initialEnergy, int gainEnergy) {
        super(SHARK, x, y);
        setMyCurrentSteps(0);
        setMyReproductionSteps(reproductionSteps);
        setMyCurrentEnergy(initialEnergy);
        setMyGainEnergy(gainEnergy);
    }
    
    @Override
    public Cell getSwapNeighbor(ArrayList<Cell> neighbors) {
        ArrayList<Cell> fish = new ArrayList<Cell>();
        Cell swapee;
        for (Cell c: neighbors) {
            if (c.getMyFutureState()==FishCell.FISH) {
                fish.add(c);
            }
        }
        if (fish.size()>0) {
            swapee = fish.get((int)Math.random()*fish.size());
            System.out.println("eatfish:"+swapee.getMyXCoordinate()+swapee.getMyYCoordinate());
            return swapee;
        }
        return getSwapNeighborHelper(neighbors);
    }
    public void decrementEnergy() {
        setMyCurrentEnergy(getMyCurrentEnergy()-1);
    }
}
