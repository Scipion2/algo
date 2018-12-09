package triangulation;

import java.util.List;


public class Triangulation
{

    List<Point> cloud;
    List<Point> enveloppeConvexe;

    public Triangulation(List <Point> src, List <Point> enveloppeConvexe) {

        this.cloud = src;
        this.enveloppeConvexe = enveloppeConvexe;

        this.makeTriangulation();

    }

    public void makeTriangulation(){

        for(int i=0; i<enveloppeConvexe.size(); i++){



        }

    }
}


