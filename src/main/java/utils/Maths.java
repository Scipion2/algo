package utils;

import triangulation.Point;

public class Maths
{

    public static class Vector
    {

        private Point[] points=new Point[2];
        private int pos_x;
        private int pos_y;

        public Vector(Point A, Point B)
        {

            points[0]=A;
            points[1]=B;

            this.pos_x=B.getX()-A.getX();
            this.pos_y=B.getY()-A.getY();

        }

        public int getX()
        {

            return this.pos_x;

        }

        public int getY()
        {

            return this.pos_y;

        }

        public int getNorme()
        {

            return (int) Math.sqrt(this.getX()*this.getX()+this.getY()*this.getY());

        }

    }


    public static boolean isAnglePositive(Point A,Point B,Point C)
    {

        Vector BA=new Vector(B,A);
        Vector BC=new Vector(B,C);

        if(prodVect(BA,BC)>0)
            return true;

        return false;

    }

    public static int prodVect(Vector AB,Vector CD)
    {

        return AB.getX()*CD.getY()-AB.getY()*CD.getX();

    }

}
