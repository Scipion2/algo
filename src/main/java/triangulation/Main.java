package triangulation;

import java.util.List;

public class Main
{
    
    public static void main(String[] args)
    {
        
        GeneratePoints test=new GeneratePoints(99,0,100,0,100);
        test.displayList();
        Convexe test1 = new Convexe(test.cloud);
        List<Point> convexeDone = test1.makeConvexe();
    }
    
}