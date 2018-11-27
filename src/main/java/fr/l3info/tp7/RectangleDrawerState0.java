package fr.l3info.tp7;

import javafx.scene.canvas.GraphicsContext;

public class RectangleDrawerState0 implements DrawerState {
    @Override
    public void mousePressed(DrawerContext context, double x, double y) {
        context.setState(new RectangleDrawerState1(x,y,0,0));
    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {

    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {

    }

    @Override
    public boolean contains ( double x, double y){
        return false;
    }

    @Override
    public void translate(double x,double y){}
}
