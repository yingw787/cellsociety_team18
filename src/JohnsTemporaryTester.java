
public class JohnsTemporaryTester {
    public static void main(String[] args) {
        Simulation mySimulation = new WaTorSimulation(1,1,1);
        Cell aa = new FishCell();
        Cell ab = new FishSharkCell(0);
        Cell ac = new FishSharkCell(0);
        Cell ba = new FishSharkCell(0);
        Cell bb = new FishSharkCell(0);
        Cell bc = new FishSharkCell(0);
        Cell ca = new FishSharkCell(0);
        Cell cb = new FishSharkCell(0);
        Cell cc = new FishSharkCell(0);
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
