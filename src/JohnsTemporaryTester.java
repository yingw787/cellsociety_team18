
public class JohnsTemporaryTester {
    public static void main(String[] args) {
        System.out.println("sdf");
        Simulation mySimulation = new SchellingSimulation(0.5);
        Cell aa = new SchellingCell(0);
        Cell ab = new SchellingCell(1);
        Cell ac = new SchellingCell(2);
        Cell ba = new SchellingCell(1);
        Cell bb = new SchellingCell(2);
        Cell bc = new SchellingCell(1);
        Cell ca = new SchellingCell(2);
        Cell cb = new SchellingCell(1);
        Cell cc = new SchellingCell(2);
        Cell[][] arr = {{aa,ab,ac},{ba,bb,bc},{ca,cb,cc}};
        GridOfCells myGrid = new GridOfCells(arr);
        mySimulation.setCellSocietyGrid(myGrid);
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
        mySimulation.step();
        print(myGrid);
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
