
public class SlimeCell extends CellWithAngleAndPatch{
    public static int occupied = 1;
    private boolean refractory, futureRefractory;
    public SlimeCell (String[] parameters) {
        super(parameters);
        getPatch().add(Integer.parseInt(parameters[this.ANGLE_PARAMETER_INDEX]));
        getFuturePatch().add(Integer.parseInt(parameters[this.ANGLE_PARAMETER_INDEX]));
        setMyCAmp(Integer.parseInt(parameters[this.PATCH_1_PARAMETER_INDEX]));
        setMyFutureCAmp(Integer.parseInt(parameters[this.PATCH_1_PARAMETER_INDEX]));
        setRefractory(false);
        setFutureRefractory(false);
    }
    public int getMyCAmp () {
        return getPatch().get(0);
    }
    public void setMyCAmp (int myCAmp) {
        getPatch().set(0, myCAmp);
    }
    public int getMyFutureCAmp () {
        return getFuturePatch().get(0);
    }
    public void setMyFutureCAmp (int myFutureCAmp) {
        getFuturePatch().set(0, myFutureCAmp);
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
