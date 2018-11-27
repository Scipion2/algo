package fr.l3info.tp7;

import javafx.scene.canvas.GraphicsContext;

public class MoveDrawerState0 implements DrawerState {
    @Override
    public void mousePressed(DrawerContext context, double x, double y) {
        for (DrawerState state : context.drawer.shapes) {
            if (state.contains(x, y))
                context.setState(new MoveDrawerState1(state,x,y));

        }
    }

        @Override
        public void mouseReleased (DrawerContext context,double x, double y){

        }

        @Override
        public void mouseMoved (DrawerContext context,double x, double y){

        }

        @Override
        public void paint (GraphicsContext graphicsContext){

        }


        @Override
        public boolean contains ( double x, double y){
        return false;
        }

        @Override
        public void translate(double x,double y){}
}
