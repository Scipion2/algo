package GUI;

import javafx.scene.canvas.GraphicsContext;

public class Circle implements Shape {
    double x,y,radius;

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(x-radius,y-radius,radius*2,radius*2);
    }

    @Override
    public boolean contains(double x, double y) {

        return Math.abs(this.x-x)<radius && Math.abs(this.y-y)<radius;
    }

    @Override
    public void translate(double dx, double dy) {
        this.x+=dx;
        this.y+=dy;
    }
}
