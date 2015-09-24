
public class SlimeCell extends CellWithAngle{
    public static int occupied = 1;
    private int myCAmp, myFutureCAmp;
    private boolean refractory, futureRefractory;
    public SlimeCell (int state, int xCoordinate, int yCoordinate, double angle, int cAmp) {
        super(state, xCoordinate, yCoordinate, angle);
        setMyCAmp(cAmp);
        setMyFutureCAmp(cAmp);
        setRefractory(false);
        setFutureRefractory(false);
    }
    public int getMyCAmp () {
        return myCAmp;
    }
    public void setMyCAmp (int myCAmp) {
        this.myCAmp = myCAmp;
    }
    public int getMyFutureCAmp () {
        return myFutureCAmp;
    }
    public void setMyFutureCAmp (int myFutureCAmp) {
        this.myFutureCAmp = myFutureCAmp;
    }
    public boolean isRefractory () {
        return refractory;
    }
    public void setRefractory (boolean refractory) {
        this.refractory = refractory;
    }
    public boolean isFutureRefractory () {
        return futureRefractory;
    }
    public void setFutureRefractory (boolean futureRefractory) {
        this.futureRefractory = futureRefractory;
    }

}
