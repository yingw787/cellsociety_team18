import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GridOfCellsFactory {

	private Document myDoc;
	private final String[] gridEdgeTypes = {"finite", "infinite", "toroidal"};
	private final String[] gridShapeTypes = {"square", "triangle", "hexagonal"};
	private final String[] gridCellNeighbors = {"cardinal", "diagonal", "all"};
	private final String[] cellInitLocation = {"location", "random", "probability"};
	
	private CellFactory myCellFactory;
	
	
	
	public GridOfCellsFactory(Document doc) {
		myDoc = doc;
		myCellFactory = new CellFactory(myDoc);
	}

	public Cell[] createCellArray() {
		String cellConfigType = ((Element) myDoc.getElementsByTagName("cellConfiguration").item(0)).getAttributes().getNamedItem("type").getNodeValue();
		if(cellConfigType.equals(cellInitLocation[0])){
			return myCellFactory.parseCells();
		}
		else if(cellConfigType.equals(cellInitLocation[1])){
			return myCellFactory.randomCellAttributes();
		}
		else if(cellConfigType.equals(cellInitLocation[2])){
			return myCellFactory.probabilityBasedCellAttributes();
		}
		else{
			throw new ParserException("Cell configuration was not specified", cellConfigType);
//			OR JUST ASSUME RANDOM??
	//		return myCellFactory.randomCellAttributes();
		}
	}
	
	public GridOfCells createGridOfCells(){
		
		return null;
	}
	
}
