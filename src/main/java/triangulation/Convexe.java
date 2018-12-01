package triangulation;

import java.util.ArrayList;
import java.util.List;


public class Convexe {

    public List<Point> cloud;
    public List<Point> convPolygon = new ArrayList<>(); //Polygon représentant la figure convexe de façon orientée

    public Convexe(List<Point> cloud){

        this.cloud = cloud;

    }

    public List<Point> makeConvexe() {

        System.out.println("Entering MakeConvexe");
        if (cloud.size() <= 3) { // Cas d'arret: 2 et 3 points, a faire arbitrairement

            System.out.println("nbPoint < 3");
            if(cloud.size() == 2){ //Jsp mais je met le plus haut en premier dans l'orientation

                System.out.println("nbpoint = 2");


                if(cloud.get(0).getY() >= cloud.get(1).getY()){
                    System.out.println("first if passed");
                    convPolygon.add(cloud.get(0));
                    convPolygon.add(cloud.get(1));
                }
                else{

                    System.out.println("point: x= " +cloud.get(1).getX());
                    System.out.println("first else passed");
                    convPolygon.add(cloud.get(1));
                    System.out.println("1 rst added");
                    convPolygon.add(cloud.get(0));
                    System.out.println("2nd added");

                }

                System.out.println("case 3 done");

                for(int i = 0; i<convPolygon.size(); i++){
                    System.out.println("Point n°" +i +": x= "+convPolygon.get(i).getX() +", y= "+convPolygon.get(i).getY());
                }

                return convPolygon;

            }
            else { // cloud.size()=3 /

                System.out.println("nbpoint = 3");

                int indexMin = 0, indexMax = 1, middle = 2;
                if(cloud.get(indexMin).getY() > cloud.get(indexMax).getY()){
                    indexMin = indexMax;
                    indexMax = 0;
                }
                if(cloud.get(indexMin).getY() > cloud.get(2).getY()){
                    middle = indexMin;
                    indexMin = 2;
                }
                else if(cloud.get(indexMax).getY() < cloud.get(2).getY()){
                    middle = indexMax;
                    indexMax = 2;
                }

                if(cloud.get(indexMax).getY()-cloud.get(middle).getY() < cloud.get(middle).getY()-cloud.get(indexMin).getY()){
                    convPolygon.add(cloud.get(middle));
                    convPolygon.add(cloud.get(indexMax));
                    convPolygon.add(cloud.get(indexMin));
                }
                else{
                    convPolygon.add(cloud.get(indexMax));
                    convPolygon.add(cloud.get(middle));
                    convPolygon.add(cloud.get(indexMin));
                }

                System.out.println("test");
                return convPolygon;
            }

        } else { // Sinon, faire cet algo

            System.out.println("nbpoint > 3");

            List<Point> leftCloud = new ArrayList<>();
            List<Point> rightCloud = new ArrayList<>();

            for (int i = 0; i < (cloud.size()) / 2; i++) {
                leftCloud.add(cloud.get(i));
            }

            for (int i = (cloud.size()) / 2; i < cloud.size(); i++) {
                rightCloud.add(cloud.get(i));
            }

            Convexe leftconv = new Convexe(leftCloud);

            System.out.println("Left conv done");

            Convexe rightconv = new Convexe(rightCloud);

            System.out.println("Right conv done");


            List<Point> leftconvpoly = leftconv.makeConvexe();

            System.out.println("Left makeconv done");

            List<Point> rightconvpoly = rightconv.makeConvexe();



            int[] upPointtofuse = searchUpFuse(leftconvpoly, rightconvpoly);
            int[] downPointtofuse = searchDownFuse(leftconvpoly, rightconvpoly);

            convPolygon = Fuse(upPointtofuse, downPointtofuse, leftconvpoly, rightconvpoly);

            return convPolygon;
        }

    }


    public int[] searchUpFuse(List<Point> leftconv, List<Point> rightconv){

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint;
        double PVECTABAC, MULTABAC, result;


        actualLeftPoint = convPolygon.size();
        actualRightPoint = 0;

        while(verif != 0){

            actualLeftPoint--;
            PVECTABAC = leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX() * leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX() + leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY() * leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY();
            MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY(), 2)));
            result = PVECTABAC/MULTABAC;
            while(result > 0){ //Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) > 0 : Angle: ABC = arccos[(BA.BC)/(BA*BC)] // BA.BC = xBA * xBC + yBA * yBC
                verif=-2; //Arrete plus haute, reset des "non"
                actualLeftPoint--;

                //Met a jour avec le nouveau point
                PVECTABAC = leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX() * leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX() + leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY() * leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY();
                MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY(), 2)));
                result = PVECTABAC/MULTABAC;

            }
            actualLeftPoint++;
            verif++; //Changement de côté car un "non"



            actualRightPoint++;
            PVECTABAC = leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX() * leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX() + leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY() * leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY();
            MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY(), 2)));
            result = PVECTABAC/MULTABAC;
            while(result < 0){ //Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) < 0
                verif=-2; //Arrete plus haute, reset des "non"
                actualRightPoint++;

                //Met a jour avec le nouveau point
                PVECTABAC = leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX() * leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX() + leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY() * leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY();
                MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY(), 2)));
                result = PVECTABAC/MULTABAC;
            }
            actualRightPoint--;
            verif++;

        }

        pointToFuse[0] = actualLeftPoint;
        pointToFuse[1] = actualRightPoint;

        return pointToFuse;
    }

    public int[] searchDownFuse(List<Point> leftconv, List<Point> rightconv){

        int[] pointToFuse = new int[2];
        int verif = -2; // Comptage de "non" (quand l'arrete suivante n'ai pas plus haute), s'il il y en a 2 d'affilé, on a la bonne arrête
        int actualLeftPoint, actualRightPoint;
        double PVECTABAC, MULTABAC, result;


        actualLeftPoint = convPolygon.size();
        actualRightPoint = 0;


        while(verif != 0){

            actualLeftPoint++;
            PVECTABAC = leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX() * leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX() + leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY() * leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY();
            MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY(), 2)));
            result = PVECTABAC/MULTABAC;
            while(result<0){ //A FAIRE: Verif si: l'angle (actualLeftPoint++,actualRightPoint, AcutalLeftpoint) < 0
                verif=-2; //Arrete plus haute, reset des "non"
                actualLeftPoint++;

                //Met a jour avec le nouveau point
                PVECTABAC = leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX() * leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX() + leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY() * leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY();
                MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualLeftPoint++).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint++).getY()-rightconv.get(actualRightPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualLeftPoint).getX()-rightconv.get(actualRightPoint).getX(), 2)+Math.pow(leftconv.get(actualLeftPoint).getY()-rightconv.get(actualRightPoint).getY(), 2)));
                result = PVECTABAC/MULTABAC;
            }
            actualLeftPoint--;
            verif++; //Changement de côté car un "non"


            actualRightPoint--;
            PVECTABAC = leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX() * leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX() + leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY() * leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY();
            MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY(), 2)));
            result = PVECTABAC/MULTABAC;
            while(result>0){ //A FAIRE: Verif si: l'angle (actualRightPoint--,actualeftPoint, AcutalRightpoint) > 0
                verif=-2; //Arrete plus haute, reset des "non"
                actualRightPoint--;


                //Met a jour avec le nouveau point
                PVECTABAC = leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX() * leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX() + leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY() * leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY();
                MULTABAC = Math.sqrt(Math.pow(leftconv.get(actualRightPoint--).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint--).getY()-rightconv.get(actualLeftPoint).getY(), 2) * Math.sqrt(Math.pow(leftconv.get(actualRightPoint).getX()-rightconv.get(actualLeftPoint).getX(), 2)+Math.pow(leftconv.get(actualRightPoint).getY()-rightconv.get(actualLeftPoint).getY(), 2)));
                result = PVECTABAC/MULTABAC;
            }
            actualRightPoint++;
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





    // public Convexe Convexe(Convexe leftPart, Convexe rightPart){

    //}
    
}