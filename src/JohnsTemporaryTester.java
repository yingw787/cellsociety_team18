import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class JohnsTemporaryTester {
    public static void main(String[] args) {
        try {
            InitializeSimulation.init(2);
        }
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Simulation mySimulation=InitializeSimulation.getDataTransfer().getMySimulation();
        GridOfCells myGrid=mySimulation.getCellSocietyGrid();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        print(myGrid);
//      Simulation mySimulation = new GameOfLifeSimulation(2,3,3);
//      Cell aa = new GameOfLifeCell(1,0,0);
//      Cell ab = new GameOfLifeCell(1,1,0);
//      Cell ac = new GameOfLifeCell(0,2,0);
//      Cell ba = new GameOfLifeCell(1,0,1);
//      Cell bb = new GameOfLifeCell(0,1,1);
//      Cell bc = new GameOfLifeCell(1,2,1);
//      Cell ca = new GameOfLifeCell(0,0,2);
//      Cell cb = new GameOfLifeCell(0,1,2);
//      Cell cc = new GameOfLifeCell(0,2,2);
//        Cell[][] arr = {{aa,ab,ac},{ba,bb,bc},{ca,cb,cc}};
//        GridOfCells myGrid = new GridOfCellsWithDiagonalNeighbors(arr);
//        mySimulation.setCellSocietyGrid(myGrid);
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        print(myGrid);
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        mySimulation.step();
//        print(myGrid);
    }
    public static void print(GridOfCells g) {
        for (int i=0;i<g.getMyCells().length;i++) {
            for (int j=0;j<g.getMyCells()[0].length;j++) {
                System.out.print(g.getMyCells()[i][j].getMyCurrentState()+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
