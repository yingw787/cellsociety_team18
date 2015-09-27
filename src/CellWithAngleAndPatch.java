import java.util.ArrayList;
import java.util.List;

public abstract class CellWithAngleAndPatch extends Cell{
    private double myAngle, myFutureAngle;
    private List<Integer> myPatch, myFuturePatch;
    public CellWithAngleAndPatch (String[] parameters) {
        super(parameters);
        System.out.println(parameters.length);
        setAngle(Double.parseDouble(parameters[this.ANGLE_PARAMETER_INDEX]));
        setFutureAngle(Double.parseDouble(parameters[this.ANGLE_PARAMETER_INDEX]));
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
    public void setOrientation(Cell c, int curX, int curY) {
        int dx = curX-c.getMyXCoordinate();
        int dy = curY-c.getMyYCoordinate();
        setAngle(Math.atan2(dy, dx));
    }
}
