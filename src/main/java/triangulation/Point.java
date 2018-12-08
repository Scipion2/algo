package triangulation;

import java.awt.Graphics;

public class Point {
    int pos_x;
    int pos_y;
    
    public Point(int x, int y)
    {
        this.pos_x = x;
        this.pos_y = y;
    }

    public int getX()
    {

        return this.pos_x;

    }

    public int getY()
    {

        return this.pos_y;

    }

    public void drawPoint(Graphics graphics,int space)
    {

        graphics.fillOval(this.pos_x*space,this.pos_y*space,10,10);

    }

}