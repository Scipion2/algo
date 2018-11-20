package fr.l3info.tp7;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class DrawerContext {

    Drawer drawer;
    DrawerState currentState;

    public DrawerContext(Drawer drawer) {
        this.drawer = drawer;
    }

    void mousePressed(MouseEvent event){
        drawer.repaint();
        paint(drawer.getGraphicsContext2D());
        double x = event.getX();
        double y = event.getY();
        currentState.mousePressed(this,x,y);

    }

    void mouseReleased(MouseEvent event){
        drawer.repaint();
        drawer.add(currentState);
        paint(drawer.getGraphicsContext2D());
        double x = event.getX();
        double y = event.getY();

        currentState.mouseReleased(this,x,y);
    }

    void mouseMoved(MouseEvent event){
        drawer.repaint();
        paint(drawer.getGraphicsContext2D());
        double x = event.getX();
        double y = event.getY();
        currentState.mouseMoved(this,x,y);
    }

    void keyPressed(KeyEvent event) {
        switch (event.getText()) {
            case "d":
                drawer.repaint();
            case "c":
                setState(new CircleDrawerState0());
        }
    }

    void paint(GraphicsContext graphicsContext){
        if(drawer.shapes.isEmpty())return;
        for (DrawerState shape:drawer.shapes
             ) {
            shape.paint(graphicsContext);
        }
    }

    Drawer drawer(){
        return drawer;
    }

    void setState(DrawerState state){
        currentState=state;
    }
}
