package GUI;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleDrawerState1 implements DrawerState {
    double x,y,height, width;

    public RectangleDrawerState1(double x, double y, double height, double width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {
        this.x=Math.min(x,this.x);

        this.y=Math.min(this.y,y);
        context.setState(new RectangleDrawerState0());
        context.drawer.repaint();
        context.paint(context.drawer.getGraphicsContext2D());
    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {
        double dx=Math.abs(x-this.x);
        double dy=Math.abs(y-this.y);
        this.width = dx;
        this.height = dy;
        context.drawer().getGraphicsContext2D().strokeRect(Math.min(x,this.x),Math.min(this.y,y),width,height);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.setFill(new Color(0,0,1,0.5));
        graphicsContext.fillRect(x,y,width,height);
    }

    @Override
    public boolean contains ( double x, double y){
        return this.x <x && x<=this.x+width && this.y <y && y<=this.y+height;
    }
    @Override
    public void translate(double x,double y){
        this.x += x;
        this.y += y;
    }
}
