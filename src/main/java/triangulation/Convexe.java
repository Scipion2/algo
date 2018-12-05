package triangulation;

import utils.Maths;

import java.util.ArrayList;
import java.util.List;


public class Convexe {

    public List<Point> cloud;
    public List<Point> convPolygon = new ArrayList<>(); //Polygon représentant la figure convexe de façon orientée

    public Convexe(List<Point> cloud)
    {

        this.cloud = cloud;
       this.makeConvexe();

    }

    public void makeConvexe()
    {

        System.out.println("Entering MakeConvexe");
        if (cloud.size() <= 3)
        { // Cas d'arret: 2 et 3 points, a faire arbitrairement

            System.out.println("nbPoint <= 3");
            if(cloud.size() == 2)
            { //Jsp mais je met le plus haut en premier dans l'orientation

                System.out.println("nbpoint = 2");


                if(cloud.get(0).getY() >= cloud.get(1).getY())
                {
                    System.out.println("first if passed");
                    convPolygon.add(cloud.get(0));
                    convPolygon.add(cloud.get(1));
                }
                else
                    {

                    System.out.println("point: x= " +cloud.get(1).getX());
                    System.out.println("first else passed");
                    convPolygon.add(cloud.get(1));
                    System.out.println("1 rst added");
                    convPolygon.add(cloud.get(0));
                    System.out.println("2nd added");

                }

                System.out.println("case 3 done");

                for(int i = 0; i<convPolygon.size(); i++)
                {
                    System.out.println("Point n°" +i +": x= "+convPolygon.get(i).getX() +", y= "+convPolygon.get(i).getY());
                }

                return ;

            }
            else
                { // cloud.size()=3 /

                System.out.println("nbpoint = 3");

                int indexMin = 0, indexMax = 1, middle = 2;
                if(cloud.get(indexMin).getY() > cloud.get(indexMax).getY())
                {
                    indexMin = indexMax;
                    indexMax = 0;
                }
                if(cloud.get(indexMin).getY() > cloud.get(2).getY())
                {
                    middle = indexMin;
                    indexMin = 2;
                }
                else if(cloud.get(indexMax).getY() < cloud.get(2).getY())
                {
                    middle = indexMax;
                    indexMax = 2;
                }

                if(cloud.get(indexMax).getY()-cloud.get(middle).getY() < cloud.get(middle).getY()-cloud.get(indexMin).getY())
                {
                    convPolygon.add(cloud.get(middle));
                    convPolygon.add(cloud.get(indexMax));
                    convPolygon.add(cloud.get(indexMin));
                }
                else
                    {
                    convPolygon.add(cloud.get(indexMax));
                    convPolygon.add(cloud.get(middle));
                    convPolygon.add(cloud.get(indexMin));
                }

                System.out.println("test");
                return ;
            }

        } else
            { // Sinon, faire cet algo

            System.out.println("nbpoint > 3");

            List<Point> leftCloud = new ArrayList<>();
            List<Point> rightCloud = new ArrayList<>();

            for (int i = 0; i < (cloud.size()) / 2; i++)
            {
                leftCloud.add(cloud.get(i));
            }

            for (int i = (cloud.size()) / 2; i < cloud.size(); i++)
            {
                rightCloud.add(cloud.get(i));
            }

            Convexe leftconv = new Convexe(leftCloud);

            System.out.println("Left conv done");

            Convexe rightconv = new Convexe(rightCloud);

            System.out.println("Right conv done");



            int[] upPointtofuse = searchUpFuse(leftconv.convPolygon, rightconv.convPolygon);
            System.out.println("up fused");
            int[] downPointtofuse = searchDownFuse(leftconv.convPolygon, rightconv.convPolygon);

            System.out.println("up and down fused");

            convPolygon = Fuse(upPointtofuse, downPointtofuse, leftconv.convPolygon, rightconv.convPolygon);

            return ;
        }

    }


    public int[] searchUpFuse(List<Point> leftconv, List<Point> rightconv){

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint, nextLeftPoint, nextRightPoint;


        actualLeftPoint = convPolygon.size()-1;
        actualRightPoint = 0;

        System.out.println("up init");

        while(verif != 0){
            System.out.println("while verif");


            nextLeftPoint = nextPoint(actualLeftPoint, convPolygon.size(), false, true);

            System.out.println("Je suis ici");

            while(Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint)))
            { //Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) > 0 : Angle: ABC = arccos[(BA.BC)/(BA*BC)] // BA.BC = xBA * xBC + yBA * yBC
                System.out.println("while right central");

                verif=-2; //Arrete plus haute, reset des "non"

                actualLeftPoint = nextLeftPoint;
                nextLeftPoint = nextPoint(nextLeftPoint, convPolygon.size(), false, true);

            }
            verif++; //Changement de côté car un "non"



            nextRightPoint = nextPoint(actualRightPoint, convPolygon.size(), true, true);

            while(!Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint)))
            { //Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) < 0
                System.out.println("while left central");

                verif=-2; //Arrete plus haute, reset des "non"

                actualRightPoint = nextRightPoint;
                nextRightPoint = nextPoint(nextRightPoint, convPolygon.size(), true, true);


            }
            verif++;

        }

        System.out.println("up done");

        pointToFuse[0] = actualLeftPoint;
        pointToFuse[1] = actualRightPoint;

        return pointToFuse;
    }

    public int[] searchDownFuse(List<Point> leftconv, List<Point> rightconv)
    {

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint, nextLeftPoint, nextRightPoint;


        actualLeftPoint = convPolygon.size()-1;
        actualRightPoint = 0;


        while(verif != 0){


            nextLeftPoint = nextPoint(actualLeftPoint, convPolygon.size(), false, false);

            while(!Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint)))
            { //Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) < 0
                verif=-2; //Arrete plus haute, reset des "non"

                actualLeftPoint = nextLeftPoint;
                nextLeftPoint = nextPoint(nextLeftPoint, convPolygon.size(), false, false);

            }
            verif++; //Changement de côté car un "non"



            nextRightPoint = nextPoint(actualRightPoint, convPolygon.size(), true, false);

            while(Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint)))
            { //Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) > 0

                verif=-2; //Arrete plus haute, reset des "non"

                actualRightPoint = nextRightPoint;
                nextRightPoint = nextPoint(nextRightPoint, convPolygon.size(), true, false);

            }
            verif++;

        }

        pointToFuse[0] = actualLeftPoint;
        pointToFuse[1] = actualRightPoint;

        return pointToFuse;
    }

    public List<Point> Fuse(int[] upPoint, int[] downPoint, List<Point> leftpoly, List<Point> rightpoly){ /* fusionne la partie gauche et la partie droite avec les points données */

        List<Point> newpoly = new ArrayList<>();

        for(int i = 0; i<upPoint[0]; i++){
            newpoly.add(leftpoly.get(i));
        }
        for(int i = upPoint[1]; i<downPoint[1]; i++){
            newpoly.add(rightpoly.get(i));
        }
        for(int i = downPoint[0]; i < leftpoly.size(); i++){
            newpoly.add(leftpoly.get(i));
        }

        return newpoly;

    }

    public int nextPoint(int actualPoint, int size, boolean rightPart, boolean Upfuse)
    {

        if (!rightPart) {
            if (Upfuse) {
                if (actualPoint > 0)
                    actualPoint--;
                else
                    actualPoint = size-1;
            }

            else
            {
                if(actualPoint<size-1)
                    actualPoint++;
                else
                    actualPoint=0;
            }

        }

        else
        {
            if (Upfuse)
            {
                if (actualPoint < size-1)
                    actualPoint++;
                else
                    actualPoint = 0;
            }

            else
            {
                if(actualPoint>0)
                    actualPoint--;
                else
                    actualPoint=size-1;
            }
        }

        return actualPoint;
    }







    // public Convexe Convexe(Convexe leftPart, Convexe rightPart){

    //}
    
}