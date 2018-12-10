package triangulation;

import utils.Maths.Vector;

import java.util.ArrayList;
import java.util.List;

public class Triangulation
{

    List<Point> cloud;
    List<Point> enveloppeConvexe;

    public Triangulation(List <Point> src, List <Point> enveloppeConvexe) {

        this.cloud = src;
        this.enveloppeConvexe = enveloppeConvexe;

    }

    public void makeTriangulation(){

        int indBaricentre = 0;
        boolean isPointinside;

        if(enveloppeConvexe.size() != 3 && cloud.size() != enveloppeConvexe.size())
            isPointinside = true;
        else
            isPointinside = isPointInsideTriangle();

        if(isPointinside) {
            indBaricentre = searchBaricentre();

            for (int i = 1; i<enveloppeConvexe.size();i++){

                List<Point> polygon = new ArrayList<>();
                polygon.add(cloud.get(indBaricentre));
                polygon.add(enveloppeConvexe.get(i-1));
                polygon.add(enveloppeConvexe.get(i));

                Triangulation insidePolygon = new Triangulation(cloud, polygon);
                insidePolygon.makeTriangulation();
            }

        }

    }

    public boolean isPointInsideTriangle()
    {

        for(int i=0; i<cloud.size(); i++)
            if(isLeft(cloud.get(i), enveloppeConvexe.get(0), enveloppeConvexe.get(1)) && isLeft(cloud.get(i), enveloppeConvexe.get(1), enveloppeConvexe.get(2)) && isLeft(cloud.get(i), enveloppeConvexe.get(2), enveloppeConvexe.get(0)))
                return true;

        return false;
    }

    public boolean isThisPointInsideTriangle(Point point)
    {

        if(isLeft(point, enveloppeConvexe.get(0), enveloppeConvexe.get(1)) && isLeft(point, enveloppeConvexe.get(1), enveloppeConvexe.get(2)) && isLeft(point, enveloppeConvexe.get(2), enveloppeConvexe.get(0)))
            return true;

        return false;
    }

    public boolean isLeft(Point point, Point A, Point B){

        if((B.pos_x - A.pos_x) * (point.pos_y - A.pos_y) - (point.pos_x - A.pos_x) * (B.pos_y - A.pos_y) > 0)
            return true;

        return false;
    }


    public int searchBaricentre(){

        int xMin = 99999, xMax = -99999, yMin = 99999, yMax = -99999;

        for(int i = 0; i<enveloppeConvexe.size(); i++) {
            if(enveloppeConvexe.get(i).getX() < xMin)
                xMin = enveloppeConvexe.get(i).getX();
            if(enveloppeConvexe.get(i).getX() > xMax)
                xMax = enveloppeConvexe.get(i).getX();
            if(enveloppeConvexe.get(i).getY() < yMin)
                yMin = enveloppeConvexe.get(i).getY();
            if(enveloppeConvexe.get(i).getY() > yMax)
                yMax = enveloppeConvexe.get(i).getY();
        }

        return searchClosestPoint(new Point(xMax - xMin, yMax - yMin));
    }

    public int searchClosestPoint(Point point){

        int indBaricentre = 0;
        Vector closest = new Vector(point, cloud.get(0));

        for(int i=1; i<cloud.size(); i++){

            if(isThisPointInsideTriangle(cloud.get(i))) {
                Vector temp = new Vector(point, cloud.get(i));
                if (temp.getNorme() < closest.getNorme()) {
                    closest = temp;
                    indBaricentre = i;
                }
            }

        }

        return indBaricentre;

    }

}

