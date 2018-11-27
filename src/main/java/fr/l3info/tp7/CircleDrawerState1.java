package fr.l3info.tp7;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CircleDrawerState1 implements DrawerState {
    double x,y,radius;

    public CircleDrawerState1(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {
        context.setState(new CircleDrawerState0());
    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {
        double dx=x-this.x;
        double dy=y-this.y;
        this.radius=Math.sqrt(dx*dx+dy*dy);
        context.drawer().getGraphicsContext2D().strokeOval(this.x-radius,this.y-radius,2*radius,2*radius);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.setFill(new Color(0,0,1,0.5));
        graphicsContext.fillOval(x-radius,y-radius,2*radius,2*radius);
    }

    @Override
    public boolean contains ( double x, double y){
        return (this.x-x)*(this.x-x)+(this.y-y)*(this.y-y)<=radius*radius;
    }

    @Override
    public void translate(double x,double y){
        this.x+=x;
        this.y+=y;
    }
}
