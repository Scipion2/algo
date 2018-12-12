package triangulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangulation
{

    private List<Convexe> conv=new ArrayList<>();
    private int convsNumber;
    public List<Point> cloud;
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
        List<Point> newCloud=new ArrayList<>();
        newCloud.addAll(actualCloud);
        newCloud=GeneratePoints.less(conv.get(convsNumber-1).convPolygon,newCloud);
        if(0!=newCloud.size())
            conv(newCloud);

    }


    private void triang()
    {


        for(int i=convsNumber-1;i>0;--i)
        {
            //System.out.println("for0");




            if(conv.get(i).convPolygon.size()<conv.get(i-1).convPolygon.size())
            {
               // System.out.println("if1");


                conv.get(i).convPolygon.get(0).degree++;
                conv.get(i-1).convPolygon.get(0).degree+=2;
                conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1).degree++;
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(0)});
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1)});

                for (int j = 1; j < conv.get(i).convPolygon.size(); ++j)
                {

                    //System.out.println("for1 :\ni = " +i +", j= " +j);

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
               // System.out.println("if2");


                conv.get(i).convPolygon.get(0).degree++;
                conv.get(i-1).convPolygon.get(0).degree+=2;
                conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1).degree++;
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(0)});
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1)});

                for (int j = 1; j < conv.get(i - 1).convPolygon.size(); ++j) {
                    //System.out.println("for2");

                    //System.out.println("i = " +i +", j= " +j);

                    conv.get(i - 1).convPolygon.get(j).degree += 2;
                    conv.get(i).convPolygon.get(j).degree++;
                    conv.get(i).convPolygon.get(j - 1).degree++;
                    Point[] arc = {conv.get(i - 1).convPolygon.get(j), conv.get(i).convPolygon.get(j)};
                    Point[] arc0 = {conv.get(i - 1).convPolygon.get(j), conv.get(i).convPolygon.get(j - 1)};

                    arcs.add(arc);
                    arcs.add(arc0);


                }
            }else
            {
                //System.out.println("else1");

                conv.get(i).convPolygon.get(0).degree++;
                conv.get(i-1).convPolygon.get(0).degree+=2;
                conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1).degree++;
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(0)});
                arcs.add(new Point[]{conv.get(i-1).convPolygon.get(0),conv.get(i).convPolygon.get(conv.get(i).convPolygon.size()-1)});

                for(int j=1;j<conv.get(i).convPolygon.size()-1;++j)
                {
                    //System.out.println("for3");

                    //System.out.println("i = " +i +", j= " +j);

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

       // System.out.println("here");
        this.particular();
       // System.out.println("here");

    }

    public void particular()
    {

        //System.out.println("particular");
        for(int i=0;i<cloud.size();++i)
        {

            //System.out.println("cloud");
            if(cloud.get(i).degree==0)
            {

                //System.out.println("0");
                cloud.get(i).degree=conv.get(convsNumber-1).convPolygon.size()-1;
                for(int j=0;j<conv.get(convsNumber-1).convPolygon.size();++j)
                {

                    arcs.add(new Point[]{cloud.get(i), conv.get(convsNumber - 1).convPolygon.get(j)});

                }

            }else if(cloud.get(i).degree==2)
            {

                //System.out.println("2");

                int convInd=0;
                int pointInd=-1;
                for(int j=0;j<convsNumber-1;++j)
                {

                    if(-1!=(pointInd=conv.get(j).convPolygon.indexOf(cloud.get(i))))
                    {
                        convInd = j;
                    }
                }

                if(0==convInd || -1==pointInd)
                    System.out.println("erreur de construction");
                else if(convInd==1)
                {

                    cloud.get(i).degree+=2;
                    conv.get(convInd-1).convPolygon.get(0).degree++;
                    conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1).degree++;

                    arcs.add(new Point[]{cloud.get(i), conv.get(convInd-1).convPolygon.get(0)});
                    arcs.add(new Point[]{cloud.get(i),conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1)});

                }else
                {

                    cloud.get(i).degree++;
                    conv.get(convInd).convPolygon.get(pointInd-1).degree++;
                    conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1).degree+=3;

                    arcs.add(new Point[]{cloud.get(i),conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1)});
                    arcs.add(new Point[]{conv.get(convInd).convPolygon.get(pointInd-1),conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1)});

                    if(pointInd==conv.get(convInd).convPolygon.size()-1)
                    {

                        arcs.add(new Point[]{conv.get(convInd).convPolygon.get(0),conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1)});
                        conv.get(convInd).convPolygon.get(0).degree++;

                    }else
                    {

                        arcs.add(new Point[]{conv.get(convInd).convPolygon.get(pointInd+1),conv.get(convInd-1).convPolygon.get(conv.get(convInd-1).convPolygon.size()-1)});
                        conv.get(convInd).convPolygon.get(pointInd+1).degree++;

                    }


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

