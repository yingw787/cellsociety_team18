package cell;

/**
 * Super class that adds the angle property to the cell.
 */
public class CellWithAngle extends Cell {
    private double myAngle, myFutureAngle;

    /**
     * Instantiates a new cell with angle.
     *
     * @param parameters the parameters
     */
    public CellWithAngle (String[] parameters) {
        super(parameters);
        setAngle(Double.parseDouble(parameters[Cell.ANGLE_PARAMETER_INDEX]));
        setFutureAngle(Double.parseDouble(parameters[Cell.ANGLE_PARAMETER_INDEX]));
    }

    /**
     * Gets the angle.
     *
     * @return the angle
     */
    public double getAngle () {
        return myAngle;
    }

    /**
     * Sets the angle.
     *
     * @param angle the new angle
     */
    public void setAngle (double angle) {
        myAngle = angle;
    }

    /**
     * Gets the future angle.
     *
     * @return the future angle
     */
    public double getFutureAngle () {
        return myFutureAngle;
    }

    /**
     * Sets the future angle.
     *
     * @param futureAngle the new future angle
     */
    public void setFutureAngle (double futureAngle) {
        myFutureAngle = futureAngle;
    }

    /**
     * Sets the orientation.
     *
     * @param c the c
     * @param curX the cur x
     * @param curY the cur y
     */
    public void setOrientation (Cell c, int curX, int curY) {
        int dx = curX - c.getXCoordinate();
        int dy = curY - c.getYCoordinate();
        setAngle(Math.atan2(dy, dx));
    }
}
