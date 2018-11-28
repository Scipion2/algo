package GUI;

import javafx.scene.canvas.GraphicsContext;

public class MoveDrawerState1 implements DrawerState {
    DrawerState state;
    double x,y;

    public MoveDrawerState1(DrawerState state,double x, double y) {
        this.state=state;
        this.x=x;
        this.y=y;
    }

    @Override
    public void mousePressed(DrawerContext context, double x, double y) {


    }

    @Override
    public void mouseReleased(DrawerContext context, double x, double y) {
        context.setState(new MoveDrawerState0());
    }

    @Override
    public void mouseMoved(DrawerContext context, double x, double y) {
        state.translate(x-this.x,y-this.y);
        this.x=x;
        this.y=y;
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
    }

    @Override
    public boolean contains ( double x, double y){
        return false;
    }

    @Override
    public void translate(double dx,double dy){}
}
