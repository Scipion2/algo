package triangulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangulation
{

    private List<Convexe> conv=new ArrayList<>();
    private int convsNumber;
    private List<Point> cloud;
    private List<Point[]> arcs= new ArrayList<>();


    public Triangulation(int numberPoint, int xmin, int xmax, int ymin, int ymax)
    {

        convsNumber=0;
        this.cloud=GeneratePoints.generatePoints(numberPoint,xmin,xmax,ymin,ymax);

        this.conv(cloud);
        this.triang();


    }

    private void conv(List<Point> actualCloud)
    {


        conv.add(new Convexe(actualCloud));
        convsNumber++;
        List<Point> newCloud=GeneratePoints.less(conv.get(convsNumber-1).convPolygon,actualCloud);
        if(0!=newCloud.size())
            conv(newCloud);

    }


    private void triang()
    {


        for(int i=convsNumber-1;i>0;--i)
        {


            if(conv.get(i).convPolygon.size()<conv.get(i-1).convPolygon.size())
            {

                for (int j = 0; j < conv.get(i).convPolygon.size(); ++j)
                {

                    conv.get(i).convPolygon.get(j).degree+=2;
                    conv.get(i-1).convPolygon.get(j).degree++;
                    conv.get(i-1).convPolygon.get(j+1).degree++;
                    Point[] arc={conv.get(i).convPolygon.get(j),conv.get(i-1).convPolygon.get(j)};
                    Point[] arc0={conv.get(i).convPolygon.get(j),conv.get(i-1).convPolygon.get(j+1)};

                    arcs.add(arc);
                    arcs.add(arc0);

                }

            }else if(conv.get(i).convPolygon.size()>conv.get(i-1).convPolygon.size())
            {

                for (int j = 0; j < conv.get(i - 1).convPolygon.size(); ++j) {

                    conv.get(i + 1).convPolygon.get(j).degree += 2;
                    conv.get(i).convPolygon.get(j).degree++;
                    conv.get(i).convPolygon.get(j - 1).degree++;
                    Point[] arc = {conv.get(i - 1).convPolygon.get(j), conv.get(i).convPolygon.get(j)};
                    Point[] arc0 = {conv.get(i - 1).convPolygon.get(j), conv.get(i).convPolygon.get(j - 1)};

                    arcs.add(arc);
                    arcs.add(arc0);


                }
            }else
                {

                    for(int j=0;j<conv.get(i).convPolygon.size()-1;++j)
                    {

                        conv.get(i).convPolygon.get(j).degree+=2;
                        conv.get(i-1).convPolygon.get(j).degree++;
                        conv.get(i-1).convPolygon.get(j-1).degree++;
                        Point[] arc={conv.get(i).convPolygon.get(j),conv.get(i-1).convPolygon.get(j)};
                        Point[] arc0={conv.get(i).convPolygon.get(j),conv.get(i-1).convPolygon.get(j-1)};

                        arcs.add(arc);
                        arcs.add(arc0);

                    }

                    conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1).degree+=2;
                    conv.get(i-1).convPolygon.get(conv.get(i).convPolygon.size()-1).degree++;
                    conv.get(i-1).convPolygon.get(0).degree++;
                    Point[] arc={conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1),conv.get(i-1).convPolygon.get(conv.get(i).convPolygon.size()-1)};
                    Point[] arc0={conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1),conv.get(i-1).convPolygon.get(0)};

                    arcs.add(arc);
                    arcs.add(arc0);

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

        for (Point[] arc : arcs)
        {

            drawArcs(graphics, factorx, factory, arc[0], arc[1]);

        }

    }

    private void drawArcs(Graphics graphics,int factorx,int factory,Point A,Point B)
    {

        graphics.drawLine(A.getX()*factorx,A.getY()*factory
                ,B.getX()*factorx,B.getY()*factory);

    }
}

