package triangulation;


import utils.QuickSort;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Comparator.*;

public class Convexe
{

    public List<Point> convPolygon = new ArrayList<>(); //Polygon représentant l'enveloppe convexe de façon orientée


    public Convexe(List<Point> src)
    {

        System.out.println("test");
        this.convPolygon=get_convex(src);
        for(int i=0;i<convPolygon.size();++i)
        {

            convPolygon.get(i).degree+=2;

        }

        System.out.println("test2");


    }

    private static void partition(List<Point> cloud, List<Point> convPolygon)
    {
        convPolygon.clear();
        if(cloud.size()<=3) // cas d'arrêt
        {
            convPolygon.addAll(cloud);
            if(cloud.size()==3 && isGoingUpDown(convPolygon.get(0), convPolygon.get(1), convPolygon.get(2))>0)
            {
                Point point = convPolygon.get(1);
                convPolygon.remove(point); convPolygon.add(point);
            }
            return;
        }


        // Sépare le nuage de point en deux parties
        List<Point> leftPart = new ArrayList<Point>();
        List<Point> rightPart = new ArrayList<Point>();

        for(int i = 0; i<cloud.size()/2; ++i)
            leftPart.add(cloud.get(i));

        for(int i = cloud.size()/2; i<cloud.size(); ++i)
            rightPart.add(cloud.get(i));

        // Création des enveloppes convexes des deux parties
        List<Point> convLeftPart = new ArrayList<Point>();
        List<Point> convRightPart = new ArrayList<Point>();
        partition(leftPart, convLeftPart);
        partition(rightPart, convRightPart);

        // Fusion des deux parties
        convPolygon.addAll( fusion(convLeftPart, convRightPart) );
    }

    private static List<Point> fusion(List<Point> leftPart, List<Point> rightPart)
    {
        // recherche du point le plus a droite dans la partie gauche;
        int iMax = 0;
        for( int i = 1; i < leftPart.size(); ++i) if(leftPart.get(i).getX() > leftPart.get(iMax).getX()) iMax = i;
        // le point le plus a gauche de la partie droite est forcément 0
        int iMin = 0;

        // Recherche des points de fusions en haut
        int actualUpLeftPoint = iMax;
        int actualUpRightPoint = iMin;
        boolean verif = true;
        while(verif){
            verif = false;
            if(isGoingUpDown(rightPart.get(actualUpRightPoint), leftPart.get(actualUpLeftPoint), leftPart.get((actualUpLeftPoint+1)%leftPart.size()))>0){
                actualUpLeftPoint = (actualUpLeftPoint+1)%leftPart.size();
                verif = true;
            }
            if(isGoingUpDown(leftPart.get(actualUpLeftPoint), rightPart.get(actualUpRightPoint), rightPart.get((actualUpRightPoint-1+rightPart.size())%rightPart.size()))<0){
                actualUpRightPoint = (actualUpRightPoint-1+rightPart.size())%rightPart.size();
                verif = true;
            }
        }


        // recherche des points de fusion en bas
        int actualDownLeftPoint = iMax;
        int actualDownRightPoint = iMin;
        verif = true;
        while(verif){
            verif = false;
            if(isGoingUpDown(rightPart.get(actualDownRightPoint), leftPart.get(actualDownLeftPoint), leftPart.get((actualDownLeftPoint-1+leftPart.size())%leftPart.size()))<0){
                actualDownLeftPoint = (actualDownLeftPoint-1+leftPart.size())%leftPart.size();
                verif = true;
            }
            if(isGoingUpDown(leftPart.get(actualDownLeftPoint), rightPart.get(actualDownRightPoint), rightPart.get((actualDownRightPoint+1)%rightPart.size()))>0){
                actualDownRightPoint = (actualDownRightPoint+1)%rightPart.size();
                verif = true;
            }
        }


        // Création de l'enveloppe convexe
        ArrayList<Point> enveloppeConvexe = new ArrayList();
        for( int i = 0; i<=actualDownLeftPoint; ++i)            enveloppeConvexe.add(leftPart.get(i));
        if(actualUpRightPoint==0){
            for( int i = actualDownRightPoint; i<rightPart.size(); ++i) enveloppeConvexe.add(rightPart.get(i));
            enveloppeConvexe.add(rightPart.get(0));
        }
        else
            for( int i = actualDownRightPoint;  i<=actualUpRightPoint; ++i)            enveloppeConvexe.add(rightPart.get(i));
        if(actualUpLeftPoint!=0) for( int i = actualUpLeftPoint; i<leftPart.size(); ++i) enveloppeConvexe.add(leftPart.get(i));


        for(int i =0; i<enveloppeConvexe.size();i++)
            System.out.println("x= " +enveloppeConvexe.get(i).getX() +", y= " +enveloppeConvexe.get(i).getY());
        System.out.println("");
        System.out.println();

        return enveloppeConvexe;
    }


    private static List<Point> get_convex(List<Point> cloud)
    {
        List<Point> enveloppeConvexe=new ArrayList<>();
        QuickSort.quickSort(enveloppeConvexe, 0, enveloppeConvexe.size());
        partition(cloud, enveloppeConvexe);
        return enveloppeConvexe;
    }

    static void isInConvexe(List<Point> convexe, List<Point> all, List<Point> not_conv){
        for (Point every : all) {
            if (!convexe.contains(every) && !not_conv.contains(every)) {
                not_conv.add(every);
            }
        }

    }


    public static double isGoingUpDown(Point A, Point B, Point C) // >0 si BC plus haut que BA, sinon plus bas.
    {
        return ((B.getX() - A.getX()) * (C.getY() - A.getY()) - (C.getX() - A.getX()) * (B.getY() - A.getY())) / 2.;
    }


    public void drawConvexe(Graphics graphics,int factorx,int factory)
    {

        System.out.println("draw");

        for(int i=0;i<convPolygon.size()-1;++i)
        {

            graphics.drawLine(convPolygon.get(i).getX()*factorx,convPolygon.get(i).getY()*factory
                    ,convPolygon.get(i+1).getX()*factorx,convPolygon.get(i+1).getY()*factory);

        }

        graphics.drawLine(convPolygon.get(convPolygon.size()-1).getX()*factorx,convPolygon.get(convPolygon.size()-1).getY()*factory
                ,convPolygon.get(0).getX()*factorx,convPolygon.get(0).getY()*factory);

    }

}

