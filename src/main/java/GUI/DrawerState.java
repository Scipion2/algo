package GUI;

import javafx.scene.canvas.GraphicsContext;

public interface DrawerState {

    void mousePressed(DrawerContext context, double x, double y);
    void mouseReleased(DrawerContext context, double x, double y);
    void mouseMoved(DrawerContext context, double x, double y);
    void paint(GraphicsContext graphicsContext);
    boolean contains(double x,double y);
    void translate(double x,double y);

}
