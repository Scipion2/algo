package triangulation;

import java.awt.*;
import java.util.ArrayList;
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

    static void drawList(Graphics graphics, int factorx, int factory, List<Point> cloud)
    {

        for (Point point : cloud) {


            Point withVector = new Point(point.getX() * factorx, point.getY() * factory);
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


