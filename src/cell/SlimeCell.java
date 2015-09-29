package cell;

/**
 * The cell for the slime simulation
 */
public class SlimeCell extends CellWithAngleAndPatch {
    public static int occupied = 1;
    private boolean refractory, futureRefractory;

    /**
     * Instantiates a new slime cell.
     *
     * @param parameters the parameters
     */
    public SlimeCell (String[] parameters) {
        super(parameters);
        getPatch().add(Integer.parseInt(parameters[Cell.ANGLE_PARAMETER_INDEX]));
        getFuturePatch().add(Integer.parseInt(parameters[Cell.ANGLE_PARAMETER_INDEX]));
        setCAmp(Integer.parseInt(parameters[Cell.PATCH_1_PARAMETER_INDEX]));
        setFutureCAmp(Integer.parseInt(parameters[Cell.PATCH_1_PARAMETER_INDEX]));
        setRefractory(false);
        setFutureRefractory(false);
    }

    /**
     * Gets the cAmp.
     *
     * @return the cAmp
     */
    public int getCAmp () {
        return getPatch().get(0);
    }

    /**
     * Sets the cAmp.
     *
     * @param cAmp the new cAmp
     */
    public void setCAmp (int cAmp) {
        getPatch().set(0, cAmp);
    }

    /**
     * Gets the future cAmp.
     *
     * @return the future cAmp
     */
    public int getFutureCAmp () {
        return getFuturePatch().get(0);
    }

    /**
     * Sets the future cAmp.
     *
     * @param futureCAmp the new future cAmp
     */
    public void setFutureCAmp (int futureCAmp) {
        getFuturePatch().set(0, futureCAmp);
    }

    /**
     * Checks if is refractory.
     *
     * @return true, if is refractory
     */
    public boolean isRefractory () {
        return refractory;
    }

    /**
     * Sets refractory.
     *
     * @param refractory the new refractory
     */
    public void setRefractory (boolean refractory) {
        this.refractory = refractory;
    }

    /**
     * Checks if is future refractory.
     *
     * @return true, if is future refractory
     */
    public boolean isFutureRefractory () {
        return futureRefractory;
    }

    /**
     * Sets future refractory.
     *
     * @param futureRefractory the new future refractory
     */
    public void setFutureRefractory (boolean futureRefractory) {
        this.futureRefractory = futureRefractory;
    }

}
