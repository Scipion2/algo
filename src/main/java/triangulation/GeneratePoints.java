package triangulation;

import java.util.List;
import utils.QuickSort;

public class GeneratePoints
{
    
    List<Point> cloud;
    
    public GeneratePoints(int pointsNumber, int xMin, int xMax, int yMin, int yMax)
    {
        
        for(int i=0;i<Math.min(pointsNumber,(xMax-xMin)*(yMax-yMin));++i)
        {

            Point newPoint;
            do
            {

                newPoint=new Point((int)(xMin+Math.random()*(xMax-xMin)),(int)(yMin+Math.random()*(yMax-yMin)));

            }while(cloud.contains(newPoint));


            this.cloud.add(newPoint);
            
        }

        utils.QuickSort.quickSort(cloud,0,cloud.size()-1);
        
    }



    public void displayList()
    {

        for(int i=0;i<cloud.size()-1;++i)
        {

            System.out.println("x="+cloud.get(i).getX()+"   y="+cloud.get(i).getY());

        }

    }
    
    
}


