import java.util.ArrayList;
import java.util.List;

public class JohnsTemporaryTester {
            public static void main(String[] args) {
                AntSpaceCell aa = new AntSpaceCell(1,0,0);
                AntSpaceCell ab = new AntSpaceCell(0,1,0);
                AntSpaceCell ac = new AntSpaceCell(0,2,0);
                AntSpaceCell ba = new AntSpaceCell(0,0,1);
                AntSpaceCell bb = new AntSpaceCell(0,1,1);
                AntSpaceCell bc = new AntSpaceCell(0,2,1);
                AntSpaceCell ca = new AntSpaceCell(0,0,2);
                AntSpaceCell cb = new AntSpaceCell(0,1,2);
                AntSpaceCell cc = new AntSpaceCell(2,2,2);
                aa.addFutureAnt(new Ant(0));
                ab.addFutureAnt(new Ant(0));
                ArrayList<Cell> r0=new ArrayList<Cell>();
                r0.add(aa);
                r0.add(ab);
                r0.add(ac);
                ArrayList<Cell> r1=new ArrayList<Cell>();
                r1.add(ba);
                r1.add(bb);
                r1.add(bc);
                ArrayList<Cell> r2=new ArrayList<Cell>();
                r2.add(ca);
                r2.add(cb);
                r2.add(cc);
                List<List<Cell>> arr = new ArrayList<List<Cell>>();
                arr.add(r0);
                arr.add(r1);
                arr.add(r2);
                EdgeProcessor edg = new NormalEdges();
                NeighborDirectionProcessor dir = new AllNeighbors();
                GridOfCells myGrid = new RectangleOrTriangleGridOfCells(arr,null,edg,dir);
             Simulation mySimulation = new AntSimulation(myGrid);

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
                for (int i=0;i<g.getMyCells().size();i++) {
                    for (int j=0;j<g.getMyCells().get(0).size();j++) {
                        //System.out.print(g.getMyCells().get(i).get(j).getMyCurrentState()+":"+((SlimeCell)(g.getMyCells().get(i).get(j))).getMyCAmp()+" ");
                        System.out.print(((AntSpaceCell)g.getMyCells().get(i).get(j)).getCurrentAnts().size());
                        System.out.print("/"+((AntSpaceCell)g.getMyCells().get(i).get(j)).getPheromones().get(0)+"/"+((AntSpaceCell)g.getMyCells().get(i).get(j)).getPheromones().get(1)+" ");
                    }
                    System.out.print("\n");
                }
                System.out.print("\n");
            }
        
}
