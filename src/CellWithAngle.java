
public class CellWithAngle extends Cell{
    private double myAngle, myFutureAngle;
    public CellWithAngle (String[] parameters) {
        super(parameters);
        setAngle(Double.parseDouble(parameters[this.angleParameterIndex]));
        setFutureAngle(Double.parseDouble(parameters[this.angleParameterIndex]));
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
