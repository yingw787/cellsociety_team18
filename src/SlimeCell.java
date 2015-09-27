
public class SlimeCell extends CellWithAngleAndPatch{
    public static int occupied = 1;
    private boolean refractory, futureRefractory;
    public SlimeCell (int state, int xCoordinate, int yCoordinate, double angle, int cAmp) {
        super(state, xCoordinate, yCoordinate, angle);
        getMyPatch().add(cAmp);
        getMyFuturePatch().add(cAmp);
        setRefractory(false);
        setFutureRefractory(false);
        setMyCAmp(cAmp);
        setMyFutureCAmp(cAmp);
    }
    public int getMyCAmp () {
        return getMyPatch().get(0);
    }
    public void setMyCAmp (int myCAmp) {
        getMyPatch().set(0, myCAmp);
    }
    public int getMyFutureCAmp () {
        return getMyFuturePatch().get(0);
    }
    public void setMyFutureCAmp (int myFutureCAmp) {
        getMyFuturePatch().set(0, myFutureCAmp);
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
