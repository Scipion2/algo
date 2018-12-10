package triangulation;

import utils.Maths;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

        for(int i=0;i<cloud.size();++i)
        {

            System.out.println("x="+cloud.get(i).getX()+"   y="+cloud.get(i).getY());

        }

    }

    public static void drawList(Graphics graphics, int factorx,int factory, List<Point> cloud)
    {

        for(int i=0;i<cloud.size();++i)
        {


           Point withVector=new Point(cloud.get(i).getX()*factorx,cloud.get(i).getY()*factory);
                 withVector.drawPoint(graphics);

        }
    }


    public static List<Point> less(List<Point> toTake, List<Point> src)
    {

        List<Point> result=src;

        for(int i=0;i<toTake.size();++i)
        {

            result.remove(toTake.get(i));

        }

        return result;

    }

    public boolean listContains(Point point,List<Point> cloud)
    {

        for(int i=0; i<cloud.size(); i++){

            if(cloud.get(i).getX() == point.getX() && cloud.get(i).getY() == point.getY())
                return true;

        }

        return false;

    }

}


