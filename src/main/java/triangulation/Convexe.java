package triangulation;

import utils.Maths;
import utils.QuickSort;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Convexe {

    public List<Point> cloud;
    public List<Point> convPolygon = new ArrayList<>(); //Polygon représentant l'enveloppe convexe de façon orientée
    public List<Point[]> arcs=new ArrayList<Point[]>();

    public Convexe(List<Point> cloud) {

        this.cloud = cloud;
        if (this.cloud.size() <= 3)
            this.convPolygon = this.makeConvexeparticular();
        else
            this.convPolygon = this.makeConvexe();


    }


    public List<Point> makeConvexeparticular() {


            System.out.println("size= "+cloud.size());

            if (cloud.size() < 3) {

                if (cloud.get(0).getY() >= cloud.get(1).getY()) {
                    convPolygon.add(cloud.get(0));
                    convPolygon.add(cloud.get(1));
                } else {
                    convPolygon.add(cloud.get(1));
                    convPolygon.add(cloud.get(0));
                }

            }

            else { // clouds.clouds.size()=3 /

            int indexMax = 0;
            if(cloud.get(indexMax).getY() < cloud.get(1).getY())
                indexMax = 1;
            if(cloud.get(indexMax).getY() < cloud.get(2).getY())
                indexMax = 2;

            System.out.println("indexMax= " +indexMax);


            if(indexMax == 0){
                System.out.println(" 0");
                convPolygon.add(cloud.get(0));
                convPolygon.add(cloud.get(2));
                convPolygon.add(cloud.get(1));
            }
            else if(indexMax == 1){
                System.out.println(" 0");

                convPolygon.add(cloud.get(1));
                convPolygon.add(cloud.get(2));
                convPolygon.add(cloud.get(0));
            }
            else{
                System.out.println(" 0");

                convPolygon.add(cloud.get(2));
                convPolygon.add(cloud.get(1));
                convPolygon.add(cloud.get(0));
            }
        }

        System.out.println("size= " +convPolygon.size());
        for(int i= 0; i<convPolygon.size(); i++)
            System.out.println("Point n°" +i +": "+"x= " +convPolygon.get(i).getX() +", y= " +convPolygon.get(i).getY());

        return convPolygon;
    }




    public List<Point> makeConvexe() {

        //Separe le nuage de point (trié sur l'abscisse) en 2
        List<Point> leftCloud = new ArrayList<>();
        List<Point> rightCloud = new ArrayList<>();


        for (int i = 0; i < (cloud.size()) / 2; i++){
            leftCloud.add(cloud.get(i));
        }


        for (int i = (cloud.size()) / 2; i < cloud.size(); i++){
            rightCloud.add(cloud.get(i));
        }


        Convexe leftconv = new Convexe(leftCloud);
        Convexe rightconv = new Convexe(rightCloud);

        System.out.println("test5");


        // crée des listes triés a partir de nos polygon pour avoir le point le plus a droite et le plus a gauche
        List<Point> sortedleftpolygon = new ArrayList<>();
        for(int i=0; i<leftconv.convPolygon.size(); i++)
            sortedleftpolygon.add(leftconv.convPolygon.get(i));
        QuickSort.quickSort(sortedleftpolygon, 0, sortedleftpolygon.size());

        List<Point> sortedrightpolygon = new ArrayList<>();
        for(int i=0; i<rightconv.convPolygon.size(); i++) {
            sortedrightpolygon.add(rightconv.convPolygon.get(i));
        }
        QuickSort.quickSort(sortedrightpolygon, 0, sortedrightpolygon.size());


        System.out.println("test6   " +sortedleftpolygon.size());

        // cherche l'index qui correspond au point le plus a gauche et le plus a droite dans nos polygon (non trié parce que orienté)
        int actualLeftPoint = searchFirstPoint(sortedleftpolygon.get(sortedleftpolygon.size()-1), leftconv.convPolygon);
        int actualRightPoint = searchFirstPoint(sortedrightpolygon.get(0), rightconv.convPolygon);


        System.out.println("fusing between:");

        System.out.print("leftconv: ");
        for (int i = 0; i < leftconv.convPolygon.size(); i++)
            System.out.print("x= " + leftconv.convPolygon.get(i).getX() + ", y= " + leftconv.convPolygon.get(i).getY() + "    ");
        System.out.println();
        System.out.print("rightconv: ");
        for (int i = 0; i < rightconv.convPolygon.size(); i++)
            System.out.print("x= " + rightconv.convPolygon.get(i).getX() + ", y= " + rightconv.convPolygon.get(i).getY() + "    ");
        System.out.println();
        System.out.println();

        //cherche les points de fusion entre les 2 polygon

        int[] upPointtofuse = searchUpFuse(leftconv.convPolygon, rightconv.convPolygon,  actualLeftPoint, actualRightPoint);
        int[] downPointtofuse = searchDownFuse(leftconv.convPolygon, rightconv.convPolygon,  actualLeftPoint, actualRightPoint);


        System.out.print("Fusing Point: upLeft: x= " + leftconv.convPolygon.get(upPointtofuse[0]).getX() + ", y= " + leftconv.convPolygon.get(upPointtofuse[0]).getY() + "     ");
        System.out.print("upright: x= " + rightconv.convPolygon.get(upPointtofuse[1]).getX() + ", y= " + rightconv.convPolygon.get(upPointtofuse[1]).getY() + "     ");
        System.out.print("downLeft: x= " + leftconv.convPolygon.get(downPointtofuse[0]).getX() + ", y= " + leftconv.convPolygon.get(downPointtofuse[0]).getY() + "     ");
        System.out.print("downright: x= " + rightconv.convPolygon.get(downPointtofuse[1]).getX() + ", y= " + rightconv.convPolygon.get(downPointtofuse[1]).getY() + "     ");
        System.out.println();


        //fusionne les 2 polygon a l'aides des points de fusion
        convPolygon = Fuse(upPointtofuse, downPointtofuse, leftconv.convPolygon, rightconv.convPolygon);

        System.out.print("fusing result: ");
        for(int i=0; i<convPolygon.size(); i++)
            System.out.print("x= " +convPolygon.get(i).getX() +", y= " +convPolygon.get(i).getY() +"    ");
        System.out.println();
        System.out.println();

        System.out.println("------------------------------------------------------------------------------------\n");


        return convPolygon;
    }



    public int[] searchUpFuse(List<Point> leftconv, List<Point> rightconv, int actualLeftPoints, int actualRightPoints) {

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint, nextLeftPoint, nextRightPoint;
        double isAnglePositive;

        actualLeftPoint = actualLeftPoints;
        actualRightPoint = actualRightPoints;

        System.out.println("going up");

        while(verif < 0){

            nextLeftPoint = nextPoint(actualLeftPoint, leftconv.size(), false, true);
            System.out.println("left: "+actualLeftPoint +", nextleft: " +nextLeftPoint +", right: "+actualRightPoint);


            isAnglePositive = Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint));

            while(isAnglePositive == 0 || ((isAnglePositive == -1) && (leftconv.get(actualLeftPoint).getY() < leftconv.get(nextLeftPoint).getY())))
            { //Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) > 0 : Angle: ABC = arccos[(BA.BC)/(BA*BC)] // BA.BC = xBA * xBC + yBA * yBC

                verif=-2; //Arrete plus haute, reset des "non"

                System.out.println("Test reussi avec: left: "+nextLeftPoint +", right: "+actualRightPoint+"\n");

                System.out.print("newleft: "+nextPoint(nextLeftPoint, leftconv.size(), false, true) +", right: "+actualRightPoint +" |||| ");

                actualLeftPoint = nextLeftPoint;
                nextLeftPoint = nextPoint(nextLeftPoint, leftconv.size(), false, true);
                isAnglePositive = Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint));
                System.out.println();


            }
            verif++; //Changement de côté car un "non"
            System.out.println("test raté\n");


            nextRightPoint = nextPoint(actualRightPoint, rightconv.size(), true, true);
            System.out.println("left: "+actualLeftPoint +", right: "+actualRightPoint +", nextRight: " +nextRightPoint);

            isAnglePositive = Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint));

            while(isAnglePositive == 1 || ((isAnglePositive == -1) && (rightconv.get(actualRightPoint).getY() < rightconv.get(nextRightPoint).getY())))
            { //Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) < 0
                verif=-2; //Arrete plus haute, reset des "non"

                System.out.println("Test reussi avec: left: "+actualLeftPoint +", right: "+nextRightPoint+"\n");

                System.out.println("left: "+actualLeftPoint + ", newright "+ nextPoint(nextRightPoint, rightconv.size(), true, true) + " |||| ");

                actualRightPoint = nextRightPoint;
                nextRightPoint = nextPoint(nextRightPoint, rightconv.size(), true, true);
                isAnglePositive = Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint));

                System.out.println();

            }

            verif++;

            System.out.println("test raté\n");


        }

        System.out.println("FOR UP: final left: " +actualLeftPoint +", final right: "+actualRightPoint);
        System.out.println();

        pointToFuse[0] = actualLeftPoint;
        pointToFuse[1] = actualRightPoint;
        return pointToFuse;
    }

    public int[] searchDownFuse(List<Point> leftconv, List<Point> rightconv, int actualLeftPoints, int actualRightPoints)
    {

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint, nextLeftPoint, nextRightPoint;
        double isAnglePositive;

        actualLeftPoint = actualLeftPoints;
        actualRightPoint = actualRightPoints;

        System.out.println("going down");

        while(verif < 0){

            nextLeftPoint = nextPoint(actualLeftPoint, leftconv.size(), false, false);
            System.out.println("left: "+actualLeftPoint +", nextleft: " +nextLeftPoint +", right: "+actualRightPoint);

            isAnglePositive = Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint));



            while(isAnglePositive == 1 || ((isAnglePositive == -1) && (leftconv.get(actualLeftPoint).getY() > leftconv.get(nextLeftPoint).getY())))
            { //Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) < 0
                verif=-2; //Arrete plus haute, reset des "non"

                System.out.println("Test reussi avec: left: "+nextLeftPoint +", right: "+actualRightPoint+"\n");

                System.out.print("newleft: "+nextPoint(nextLeftPoint, leftconv.size(), false, true) +", right: "+actualRightPoint +" |||| ");

                actualLeftPoint = nextLeftPoint;
                nextLeftPoint = nextPoint(nextLeftPoint, leftconv.size(), false, false);
                isAnglePositive = Maths.isAnglePositive(leftconv.get(actualLeftPoint),rightconv.get(actualRightPoint),leftconv.get(nextLeftPoint));
                System.out.println();

            }
            verif++; //Changement de côté car un "non"
            System.out.println("test raté\n");

            nextRightPoint = nextPoint(actualRightPoint, rightconv.size(), true, false);
            System.out.println("left: "+actualLeftPoint +", right: "+actualRightPoint +", nextRight: " +nextRightPoint + " |||| ");


            isAnglePositive = Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint));

            while(isAnglePositive == 0 || ((isAnglePositive == -1) && (rightconv.get(actualRightPoint).getY() > rightconv.get(nextRightPoint).getY())))
            { //Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) > 0
                verif=-2; //Arrete plus haute, reset des "non"

                System.out.println("Test reussi avec: left: "+actualLeftPoint +", right: "+nextRightPoint+"\n");

                System.out.print("left: "+actualLeftPoint + ", newright "+ nextPoint(nextRightPoint, rightconv.size(), true, true) + " |||| ");

                actualRightPoint = nextRightPoint;
                nextRightPoint = nextPoint(nextRightPoint, rightconv.size(), true, false);
                isAnglePositive = Maths.isAnglePositive(rightconv.get(actualRightPoint),leftconv.get(actualLeftPoint),rightconv.get(nextRightPoint));

                System.out.println();

            }
            verif++;
            System.out.println("test raté\n");

        }

        System.out.println("FOR DOWN: final left: " +actualLeftPoint +", final right: "+actualRightPoint);
        System.out.println();

        pointToFuse[0] = actualLeftPoint;
        pointToFuse[1] = actualRightPoint;

        return pointToFuse;
    }



    /*public List<Point> Fuse(int[] upPoint, int[] downPoint, List<Point> leftpoly, List<Point> rightpoly) {

        List<Point> newPoly = new ArrayList<>();

        int actualPoint = upPoint[1];
        for (int i = 0; i < rightpoly.size(); i++) {

            if (rightpoly.get(actualPoint).getX() >= rightpoly.get(upPoint[0]).getX())
                newPoly.add(rightpoly.get(actualPoint));

            if (actualPoint == rightpoly.size() - 1) {
                actualPoint = 0;
            } else
                actualPoint++;

            if (actualPoint == downPoint[1]) {
                newPoly.add(rightpoly.get(downPoint[1]));
                break;
            }
        }


        actualPoint = downPoint[0];
        for (int i = 0; i < leftpoly.size(); i++) {

            if (leftpoly.get(actualPoint).getX() <= leftpoly.get(upPoint[0]).getX())
                newPoly.add(leftpoly.get(actualPoint));

            if (actualPoint == leftpoly.size() - 1) {
                actualPoint = 0;
            } else
                actualPoint++;

            if (actualPoint == upPoint[0]) {
                newPoly.add(leftpoly.get(upPoint[0]));
                break;
            }

        }


        return newPoly;
    }*/




    public List<Point> Fuse(int[] upPoint, int[] downPoint, List<Point> leftpoly, List<Point> rightpoly){  //fusionne la partie gauche et la partie droite avec les points données

        List<Point> newpoly = new ArrayList<>();

        if(upPoint[0] == downPoint[0]){
            newpoly.add(leftpoly.get(upPoint[0]));

            for (int i = upPoint[1]; i <= downPoint[1]; i++) {
                newpoly.add(rightpoly.get(i));
            }

        }
        else if(upPoint[1] == downPoint[1]){

            newpoly.add(leftpoly.get(upPoint[0]));
            newpoly.add(rightpoly.get(upPoint[1]));
            for (int i = downPoint[0]; i < leftpoly.size(); i++) {
                newpoly.add(leftpoly.get(i));
            }

        }
        else {

            if(leftpoly.get(upPoint[0]).getX() == rightpoly.get(upPoint[1]).getX() && leftpoly.get(downPoint[0]).getX() == rightpoly.get(downPoint[1]).getX()) {
                if (leftpoly.get(upPoint[0]).getY() < rightpoly.get(upPoint[1]).getY()) {
                    for (int i = upPoint[1]; i <= downPoint[1]; i++) {
                        newpoly.add(rightpoly.get(i));
                    }
                    for (int i = upPoint[0]; i <= downPoint[0]; i++) {
                        newpoly.add(leftpoly.get(i));
                    }
                    return newpoly;
                } else {
                    for (int i = upPoint[0]; i <= downPoint[0]; i++) {
                        newpoly.add(leftpoly.get(i));
                    }
                    for (int i = upPoint[1]; i <= downPoint[1]; i++) {
                        newpoly.add(rightpoly.get(i));
                    }
                    return newpoly;
                }
            }


            if (leftpoly.get(upPoint[0]).getY() < rightpoly.get(upPoint[1]).getY()) {

                for (int i = upPoint[1]; i <= downPoint[1]; i++) {
                    newpoly.add(rightpoly.get(i));
                }
                for (int i = downPoint[0]; i < leftpoly.size(); i++) {
                    newpoly.add(leftpoly.get(i));
                }
                newpoly.add(leftpoly.get(upPoint[0]));
                return newpoly;

            }

            else{
                newpoly.add(leftpoly.get(upPoint[0]));
                for (int i = upPoint[1]; i <= downPoint[1]; i++) {
                    newpoly.add(rightpoly.get(i));
                }
                for (int i = downPoint[0]; i < leftpoly.size(); i++) {
                    newpoly.add(leftpoly.get(i));
                }


            }
            return newpoly;

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
                if(size>0)
                    actualPoint = size-1;
                else
                    actualPoint++;
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
                if(size>0)
                    actualPoint=size-1;
                else
                    actualPoint ++;
            }
        }

        return actualPoint;
    }

    public int searchFirstPoint(Point pointToFind, List<Point> polygon){

        for(int i=0; i<polygon.size(); i++) {
            if ((pointToFind.getX() == polygon.get(i).getX()) && (pointToFind.getY() == polygon.get(i).getY())) {
                return i;
            }
        }

        return -1;
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