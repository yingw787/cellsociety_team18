public class JohnsTemporaryTester {
    public static void main(String[] args) {
      Simulation mySimulation = new SpreadingFireSimulation(0.5);
      Cell aa = new TreeCell(2,0,0);
      Cell ab = new TreeCell(2,1,0);
      Cell ac = new TreeCell(2,2,0);
      Cell ba = new TreeCell(2,0,1);
      Cell bb = new TreeCell(1,1,1);
      Cell bc = new TreeCell(2,2,1);
      Cell ca = new TreeCell(2,0,2);
      Cell cb = new TreeCell(2,1,2);
      Cell cc = new TreeCell(2,2,2);
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
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
        mySimulation.step();
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
