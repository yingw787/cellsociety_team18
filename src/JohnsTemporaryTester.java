
public class JohnsTemporaryTester {
    public static void main(String[] args) {
//        Simulation mySimulation = new SchellingSimulation(0.5);
//        Cell aa = new SchellingCell(0,0,0);
//        Cell ab = new SchellingCell(1,1,0);
//        Cell ac = new SchellingCell(2,2,0);
//        Cell ba = new SchellingCell(1,0,1);
//        Cell bb = new SchellingCell(2,1,1);
//        Cell bc = new SchellingCell(1,2,1);
//        Cell ca = new SchellingCell(2,0,2);
//        Cell cb = new SchellingCell(1,1,2);
//        Cell cc = new SchellingCell(2,2,2);
        Simulation mySimulation = new WaTorSimulation(1,1,1);
        Cell aa = new FishCell(0,0);
//        Cell ab = new FishCell(1,0);
//        Cell ac = new FishCell(2,0);
//        Cell ba = new FishCell(0,1);
//        Cell bb = new FishCell(1,1);
//        Cell bc = new FishCell(2,1);
//        Cell ca = new FishCell(0,2);
        Cell ab = new SharkCell(1,0);
        Cell ac = new FishSharkCell(0,2,0);
        Cell ba = new FishSharkCell(0,0,1);
        Cell bb = new FishSharkCell(0,1,1);
        Cell bc = new FishSharkCell(0,2,1);
        Cell ca = new FishSharkCell(0,0,2);
        Cell cb = new FishSharkCell(0,1,2);
        Cell cc = new FishSharkCell(0,2,2);
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
