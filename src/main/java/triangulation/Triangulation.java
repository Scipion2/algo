package triangulation;

import utils.Maths.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangulation
{

    List<Convexe> conv;
    int convsNumber;
    List<Point> cloud;
    List<Point[]> arcs=new ArrayList<Point[]>();


    public Triangulation(int numberPoint, int xmin, int xmax, int ymin, int ymax)
    {

        convsNumber=0;
        this.cloud=GeneratePoints.generatePoints(numberPoint,xmin,xmax,ymin,ymax);

        this.conv(cloud);
        this.triang();


    }

    private void conv(List<Point> actualCloud)
    {

        conv.add(convsNumber++,new Convexe(actualCloud));
        List<Point> newCloud=GeneratePoints.less(conv.get(convsNumber-1).convPolygon,actualCloud);

        if(0!=newCloud.size())
            conv(newCloud);

    }


    private void triang()
    {


        for(int i=convsNumber-1;i>0;--i)
        {


            if(conv.get(i).convPolygon.size()<conv.get(i+1).convPolygon.size())
            {

                for (int j = 0; j < conv.get(i).convPolygon.size(); ++j)
                {

                    conv.get(i).convPolygon.get(j).degree+=2;
                    conv.get(i+1).convPolygon.get(j).degree++;
                    conv.get(i+1).convPolygon.get(j+1).degree++;
                    Point[] arc={conv.get(i).convPolygon.get(j),conv.get(i+1).convPolygon.get(j)};
                    Point[] arc0={conv.get(i).convPolygon.get(j),conv.get(i+1).convPolygon.get(j+1)};

                    arcs.add(arc);
                    arcs.add(arc0);

                }

            }else
            {

                for(int j=0;j<conv.get(i+1).convPolygon.size();++j)
                {

                    conv.get(i+1).convPolygon.get(j).degree+=2;
                    conv.get(i).convPolygon.get(j).degree++;
                    conv.get(i).convPolygon.get(j+1).degree++;
                    Point[] arc={conv.get(i+1).convPolygon.get(j),conv.get(i).convPolygon.get(j)};
                    Point[] arc0={conv.get(i+1).convPolygon.get(j),conv.get(i).convPolygon.get(j+1)};

                    arcs.add(arc);
                    arcs.add(arc0);


                }

            }

        }



    }


    public void drawTriang(Graphics graphics,int factorx, int factory)
    {

        GeneratePoints.drawList(graphics,factorx,factory,this.cloud);

        for(int i=0;i<convsNumber;++i)
        {

            conv.get(i).drawConvexe(graphics,factorx,factory);

        }

        for(int i=0;i<arcs.size();++i)
        {

            drawArcs(graphics,factorx,factory,arcs.get(i)[0],arcs.get(i)[1]);

        }

    }

    private void drawArcs(Graphics graphics,int factorx,int factory,Point A,Point B)
    {

        graphics.drawLine(A.getX()*factorx,A.getY()*factory
                ,B.getX()*factorx,B.getY()*factory);

    }
}

