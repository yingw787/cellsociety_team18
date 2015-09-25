package not.relevant;

//public class JohnsTemporaryTester {
//            public static void main(String[] args) {
//                Cell aa = new SlimeCell(1,0,0,0,0);
//                Cell ab = new SlimeCell(0,1,0,0,20);
//                Cell ac = new SlimeCell(0,2,0,0,0);
//                Cell ba = new SlimeCell(0,0,1,0,0);
//                Cell bb = new SlimeCell(0,1,1,0,0);
//                Cell bc = new SlimeCell(0,2,1,0,0);
//                Cell ca = new SlimeCell(0,0,2,0,0);
//                Cell cb = new SlimeCell(0,1,2,0,0);
//                Cell cc = new SlimeCell(0,2,2,0,0);
//                ArrayList<Cell> r0=new ArrayList<Cell>();
//                r0.add(aa);
//                r0.add(ab);
//                r0.add(ac);
//                ArrayList<Cell> r1=new ArrayList<Cell>();
//                r1.add(ba);
//                r1.add(bb);
//                r1.add(bc);
//                ArrayList<Cell> r2=new ArrayList<Cell>();
//                r2.add(ca);
//                r2.add(cb);
//                r2.add(cc);
//                List<List<Cell>> arr = new ArrayList<List<Cell>>();
//                arr.add(r0);
//                arr.add(r1);
//                arr.add(r2);
//                EdgeProcessor edg = new NormalEdges();
//                NeighborDirectionProcessor dir = new CardinalNeighbors();
//                GridOfCells myGrid = new RectangleOrTriangleGridOfCells(arr,null,edg,dir);
//             Simulation mySimulation = new SlimeSimulation(myGrid,0.5,180,1,180);
//
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                print(myGrid);
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                mySimulation.step();
//                print(myGrid);
//            }
//            public static void print(GridOfCells g) {
//                for (int i=0;i<g.getMyCells().size();i++) {
//                    for (int j=0;j<g.getMyCells().get(0).size();j++) {
//                        System.out.print(g.getMyCells().get(i).get(j).getMyCurrentState()+":"+((SlimeCell)(g.getMyCells().get(i).get(j))).getMyCAmp()+" ");
//                    }
//                    System.out.print("\n");
//                }
//                System.out.print("\n");
//            }
//        
//}
