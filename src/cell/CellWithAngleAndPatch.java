package cell;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Super class that adds the property patch, a list of integers, to CellWithAngle
 */
public abstract class CellWithAngleAndPatch extends CellWithAngle{
    private List<Integer> myPatch, myFuturePatch;
    
    /**
     * Instantiates a new cell with angle and patch.
     *
     * @param parameters the parameters
     */
    public CellWithAngleAndPatch (String[] parameters) {
        super(parameters);
        myPatch = new ArrayList<Integer>();
        myFuturePatch = new ArrayList<Integer>();
    }
    
    /**
     * Gets the patch.
     *
     * @return the patch
     */
    public List<Integer> getPatch () {
        return myPatch;
    }
    
    /**
     * Sets the patch.
     *
     * @param patch the new patch
     */
    public void setPatch (List<Integer> patch) {
        this.myPatch = patch;
    }
    
    /**
     * Gets the future patch.
     *
     * @return the future patch
     */
    public List<Integer> getFuturePatch () {
        return myFuturePatch;
    }
    
    /**
     * Sets the future patch.
     *
     * @param futurePatch the new future patch
     */
    public void setFuturePatch (List<Integer> futurePatch) {
        this.myFuturePatch = futurePatch;
    }
}
