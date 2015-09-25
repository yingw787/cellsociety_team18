import java.util.ArrayList;
import java.util.List;

public class CellWithAngleAndPatch extends Cell{
    private double myAngle, myFutureAngle;
    private List<Integer> myPatch, myFuturePatch;
    public CellWithAngleAndPatch (int state, int xCoordinate, int yCoordinate, double angle) {
        super(state, xCoordinate, yCoordinate);
        setAngle(angle);
        setFutureAngle(angle);
        myPatch = new ArrayList<Integer>();
        myFuturePatch = new ArrayList<Integer>();
        // TODO Auto-generated constructor stub
    }
    public double getAngle () {
        return myAngle;
    }
    public void setAngle (double angle) {
        this.myAngle = angle;
    }
    public double getFutureAngle () {
        return myFutureAngle;
    }
    public void setFutureAngle (double futureAngle) {
        this.myFutureAngle = futureAngle;
    }
    public List<Integer> getMyPatch () {
        return myPatch;
    }
    public void setMyPatch (List<Integer> myPatch) {
        this.myPatch = myPatch;
    }
    public List<Integer> getMyFuturePatch () {
        return myFuturePatch;
    }
    public void setMyFuturePatch (List<Integer> myFuturePatch) {
        this.myFuturePatch = myFuturePatch;
    }

}
