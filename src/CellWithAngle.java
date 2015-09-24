
public class CellWithAngle extends Cell{
    private double myAngle, myFutureAngle;
    public CellWithAngle (int state, int xCoordinate, int yCoordinate, double angle) {
        super(state, xCoordinate, yCoordinate);
        setAngle(angle);
        setFutureAngle(angle);
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

}
