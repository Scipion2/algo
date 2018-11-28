package triangulation;

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
}