package triangulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeneratePoints
{
    

   public static List<Point> generatePoints(int pointsNumber, int xMin, int xMax, int yMin, int yMax)
    {

        List<Point> cloud = new ArrayList<>();

        for(int i=0;i<Math.min(pointsNumber,(xMax-xMin)*(yMax-yMin));++i) {

            Point newPoint;
            do {

                newPoint = new Point((int) (xMin + Math.random() * (xMax - xMin)), (int) (yMin + Math.random() * (yMax - yMin)));

            } while (cloud.contains(newPoint));

            cloud.add(newPoint);
        }

        utils.QuickSort.quickSort(cloud,0,cloud.size());

        return cloud;
        
    }

    public void displayList(List<Point> cloud)
    {

        for (Point point : cloud) {

            System.out.println("x=" + point.getX() + "   y=" + point.getY());

        }

    }

    public static void drawList(Graphics graphics, int factorx, int factory, List<Point> cloud)
    {


        for(int i=0;i<cloud.size();++i)
        {

            System.out.println(cloud.size());

            Point withVector = new Point(cloud.get(i).getX() * factorx, cloud.get(i).getY() * factory);
            //System.out.println("I.X ="+cloud.get(i).getX()+"  I.Y ="+cloud.get(i).getY()+"\nwithVector X="+withVector.getX()+" Y="+withVector.getY());
            withVector.drawPoint(graphics);

        }
    }


    static List<Point> less(List<Point> toTake, List<Point> src)
    {

        List<Point> result=src;

        for (Point point : toTake) {

            result.remove(point);

        }

        return result;

    }

    public boolean listContains(Point point,List<Point> cloud)
    {

        for (Point point1 : cloud) {

            if (point1.getX() == point.getX() && point1.getY() == point.getY())
                return true;

        }

        return false;

    }

}


