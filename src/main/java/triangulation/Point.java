package triangulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public void drawPoint(GraphicsContext graphicsContext)
    {

        graphicsContext.setFill(new Color(0,0,1,0.5));
        graphicsContext.fillOval(this.pos_x,this.pos_y,10,10);

    }

}