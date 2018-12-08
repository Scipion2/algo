package triangulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratePoints
{
    
    List<Point> cloud = new ArrayList<>();

   public GeneratePoints(int pointsNumber, int xMin, int xMax, int yMin, int yMax)
    {

        for(int i=0;i<Math.min(pointsNumber,(xMax-xMin)*(yMax-yMin));++i) {

            Point newPoint;
            do {

                newPoint = new Point((int) (xMin + Math.random() * (xMax - xMin)), (int) (yMin + Math.random() * (yMax - yMin)));

            } while (cloud.contains(newPoint));

            this.cloud.add(newPoint);
        }

        utils.QuickSort.quickSort(cloud,0,cloud.size()-1);
        
    }

    public void displayList()
    {

        for(int i=0;i<cloud.size();++i)
        {

            System.out.println("x="+cloud.get(i).getX()+"   y="+cloud.get(i).getY());

        }

    }

    public void drawList(Graphics graphics,int space)
    {

        for(int i=0;i<cloud.size();++i)
        {

           cloud.get(i).drawPoint(graphics,space);

        }

    }

}


