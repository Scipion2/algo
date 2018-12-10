package triangulation;

import GUI.Window;

public class Main
{
    
    public static void main(String[] args)
    {


         Window win= new Window();




        //Test aléatoire
        /*GeneratePoints test=new GeneratePoints(20000,0,1000,0,1000);
        test.displayList();


        Convexe test1 = new Convexe(test.clouds);


        test1.makeConvexe();
        for(int i=0; i<test1.convPolygon.size(); i++){
            System.out.println("Point n°" +i +": x= " +test1.convPolygon.get(i).getX() +", y= " +test1.convPolygon.get(i).getY());
        }
        Triangulation triangulation = new Triangulation(test1.clouds, test1.convPolygon);
        triangulation.makeTriangulation();
        */




        // Test cas particulier
        /*List<Point> testConv = new ArrayList<>();
        testConv.add(new Point(70, 10));
        testConv.add(new Point(100, 20));
        testConv.add(new Point(40, 30));
        testConv.add(new Point(50, 40));
        testConv.add(new Point(70, 40));
        testConv.add(new Point(120, 40));
        testConv.add(new Point(90, 50));
        testConv.add(new Point(110, 50));
        testConv.add(new Point(30, 60));
        testConv.add(new Point(60, 60));
        testConv.add(new Point(120, 60));
        testConv.add(new Point(140, 60));
        testConv.add(new Point(40, 70));
        testConv.add(new Point(70, 70));
        testConv.add(new Point(100, 70));
        testConv.add(new Point(120, 70));
        testConv.add(new Point(20, 80));
        testConv.add(new Point(50, 80));
        testConv.add(new Point(90, 80));
        testConv.add(new Point(140, 80));
        testConv.add(new Point(40, 90));
        testConv.add(new Point(60, 90));
        testConv.add(new Point(80, 90));
        testConv.add(new Point(10, 80));
        testConv.add(new Point(110, 90));
        testConv.add(new Point(20, 100));
        testConv.add(new Point(40, 100));
        testConv.add(new Point(70, 100));
        testConv.add(new Point(100, 100));
        testConv.add(new Point(50, 110));
        testConv.add(new Point(90, 110));
        testConv.add(new Point(80, 120));
        QuickSort.quickSort(testConv, 0, testConv.size());

        for(int i=0; i<testConv.size(); i++){
            System.out.println("Point n°" +i +": x= " +testConv.get(i).getX() +", y= " +testConv.get(i).getY());
        }

        Convexe test2 = new Convexe(testConv);

        System.out.println("Convexe result: ");
        for(int i=0; i<test2.convPolygon.size(); i++){
            System.out.println("Point n°" +i +": x= " +test2.convPolygon.get(i).getX() +", y= " +test2.convPolygon.get(i).getY());
        }

        Window win2=new Window(test2);*/

        /*Triangulation triangulation = new Triangulation(test2.clouds, test2.convPolygon);
        triangulation.makeTriangulation();*/


    }
    
}