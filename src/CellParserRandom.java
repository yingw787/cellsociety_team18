import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import javafx.scene.shape.Shape;

public class CellParserRandom extends CellParser {

    public CellParserRandom (Element gridConfigurationTag, String simulationType, ResourceBundle resourceBundle, int[] bounds) {
        super(gridConfigurationTag, simulationType, resourceBundle, bounds);
    }

    @Override
    List<Cell> parseCells () {
        List<Cell> cells = new ArrayList<Cell>();
        int index;
        System.out.print("Indexs: ");
        for(int i=0; i<this.myBounds[0]; i++){
            for(int j=0; j<this.myBounds[1]; j++){
                 index = i*this.myBounds[0]+j;
                 String[] properties = getCellProperties(i,j);
                 Shape shape = getShape();
                 Cell newCell = this.createCell(properties, shape);
       System.out.print(index);
                 cells.add(index, newCell);
            }
        }
        return cells;
    }

    private Shape getShape(){
        try {
            Constructor<?> c = Class.forName(myResource.getString(this.DEFAULT_SHAPE)).getConstructor();
            return (Shape) c.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ParserException("Could not create default shape for randomly genereated cell");
        }
    }
    
    private String[] getCellProperties (int X, int Y) {
        String state = generateStateRandomly();
        String x = new Integer(X).toString();
        String y = new Integer(Y).toString();
        String angle = this.myResource.getString(this.DEFAULT_ANGLE);
        return new String[]{state,x,y,angle};
    }

    private String generateStateRandomly () {
        int numOfStates = this.myCellTypesForState.length;
        double random = Math.random();
        double chanceForState = 1.0/numOfStates;
        for(int i=0; i<numOfStates; i++){
            if(random>=i*chanceForState && random<(i+1)*chanceForState){
                return new Integer(i).toString();
            }
        }
        
        return this.myResource.getString(this.DEFAULT_STATE);
    }

}
