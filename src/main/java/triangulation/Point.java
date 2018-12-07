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

    public void drawPoint(Graphics graphics)
    {

        graphics.fillOval(this.pos_x*2,this.pos_y+2,10,10);

    }

}