package GUI;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    public void paint(GraphicsContext graphicsContext);
    public boolean contains(double x,double y);
    public void translate(double dx,double dy);
}
