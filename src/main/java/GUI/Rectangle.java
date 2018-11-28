package GUI;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle implements Shape {
    double x,y,width,height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(x,y,width,height);
    }

    @Override
    public boolean contains(double x, double y) {

        return Math.abs(this.x+width)-x>Math.abs(width) && Math.abs(this.y+height)-y>Math.abs(height);
    }

    @Override
    public void translate(double dx, double dy) {
        this.x+=dx;
        this.y+=dy;
    }
}
