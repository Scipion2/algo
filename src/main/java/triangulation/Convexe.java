package triangulation;


import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Comparator.*;

public class Convexe
{

    public List<Point> convPolygon = new ArrayList<>(); //Polygon représentant l'enveloppe convexe de façon orientée


    public Convexe(List<Point> src)
    {

        this.convPolygon=get_convex(src);
        for(int i=0;i<convPolygon.size();++i)
        {

            convPolygon.get(i).degree+=2;

        }

    }

    public static double surface(Point firstPoint, Point seconPoint, Point thirdPoint)
    {
        // retourne la surface
        // négatif si on tourne dans le sens contraire des aiguilles d'une montre
        // positif sinon
        return ((seconPoint.getX() - firstPoint.getX()) * (thirdPoint.getY() - firstPoint.getY()) - (thirdPoint.getX() - firstPoint.getX()) * (seconPoint.getY() - firstPoint.getY())) / 2.;
    }

    private static void partition(List<Point> cloud, List<Point> conv)
    {
        conv.clear();
        if(cloud.size()<=3)
        {
            conv.addAll(cloud);
            if(cloud.size()==3 && surface(conv.get(0), conv.get(1), conv.get(2))>0)
            {
                Point point = conv.get(1);
                conv.remove(point); conv.add(point);
            }
            return;
        }
        //On divise en deux
        List<Point> firstHalf = new ArrayList<Point>();
        List<Point> secondHalf = new ArrayList<Point>();

        for(int i = 0; i<cloud.size()/2; ++i)
            firstHalf.add(cloud.get(i));

        for(int i = cloud.size()/2; i<cloud.size(); ++i)
            secondHalf.add(cloud.get(i));

        // On calcule les enveloppes de chaque morceau
        List<Point> convFirstHalf = new ArrayList<Point>();
        List<Point> convSecondHalf = new ArrayList<Point>();
        partition(firstHalf, convFirstHalf);
        partition(secondHalf, convSecondHalf);

        // et on fusionne !
        conv.addAll( fusion(convFirstHalf, convSecondHalf) );
    }

    private static List<Point> fusion(List<Point> firstHalf, List<Point> secondHalf)
    {
        // recherche du point d'abcisse le plus grand dans firstHalf;
        int iMax = 0;
        for( int i = 1; i < firstHalf.size(); ++i) if(firstHalf.get(i).getX() > firstHalf.get(iMax).getX()) iMax = i;
        // le point d'abcisse le plus petit dans secondHalf est en 0
        int iMin = 0;
        // le segment du haut
        int ip1 = iMax;
        int ip2 = iMin;
        boolean verif = true;
        while(verif){
            verif = false;
            if(surface(secondHalf.get(ip2), firstHalf.get(ip1), firstHalf.get((ip1+1)%firstHalf.size()))>0){
                ip1 = (ip1+1)%firstHalf.size();
                verif = true;
            }
            if(surface(firstHalf.get(ip1), secondHalf.get(ip2), secondHalf.get((ip2-1+secondHalf.size())%secondHalf.size()))<0){
                ip2 = (ip2-1+secondHalf.size())%secondHalf.size();
                verif = true;
            }
        }
        // le segment du bas
        int im1 = iMax;
        int im2 = iMin;
        verif = true;
        while(verif){
            verif = false;
            if(surface(secondHalf.get(im2), firstHalf.get(im1), firstHalf.get((im1-1+firstHalf.size())%firstHalf.size()))<0){
                im1 = (im1-1+firstHalf.size())%firstHalf.size();
                verif = true;
            }
            if(surface(firstHalf.get(im1), secondHalf.get(im2), secondHalf.get((im2+1)%secondHalf.size()))>0){
                im2 = (im2+1)%secondHalf.size();
                verif = true;
            }
        }
        // fabrication de l'enveloppe
        ArrayList<Point> env = new ArrayList();
        for( int i = 0; i<=im1; ++i)            env.add(firstHalf.get(i));
        if(ip2==0){
            for( int i = im2; i<secondHalf.size(); ++i) env.add(secondHalf.get(i));
            env.add(secondHalf.get(0));
        }
        else
            for( int i = im2;  i<=ip2; ++i)            env.add(secondHalf.get(i));
        if(ip1!=0) for( int i = ip1; i<firstHalf.size(); ++i) env.add(firstHalf.get(i));

        return env;
    }

    //on prend un arraylist de points en entrée et on renvoie un array list qui représente l'enveloppe convexe.
    private static List<Point> get_convex(List<Point> cloud)
    {
        List<Point> env=new ArrayList<>();
        cloud.sort(comparingInt(o -> o.getX()));
        partition(cloud, env);
        return env;
    }

    static void isInConvexe(List<Point> convexe, List<Point> all, List<Point> not_conv){
        for (Point every : all) {
            if (!convexe.contains(every) && !not_conv.contains(every)) {
                not_conv.add(every);
            }
        }

    }


    public void drawConvexe(Graphics graphics,int factorx,int factory)
    {

        for(int i=0;i<convPolygon.size()-1;++i)
        {

            graphics.drawLine(convPolygon.get(i).getX()*factorx,convPolygon.get(i).getY()*factory
                    ,convPolygon.get(i+1).getX()*factorx,convPolygon.get(i+1).getY()*factory);

        }

        graphics.drawLine(convPolygon.get(convPolygon.size()-1).getX()*factorx,convPolygon.get(convPolygon.size()-1).getY()*factory
                ,convPolygon.get(0).getX()*factorx,convPolygon.get(0).getY()*factory);

    }

}

