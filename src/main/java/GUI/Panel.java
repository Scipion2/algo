package GUI;

import triangulation.GeneratePoints;

import java.awt.Graphics;
import javax.swing.JPanel;



public class Panel extends JPanel {

    public void go(Graphics graphics,String[] values)
    {

        int[] data=new int[5];
        for(int i=0;i<5;++i)
        {

            data[i]=Integer.parseInt(values[i]);

        }

        GeneratePoints myCloud=new GeneratePoints(data[0],data[1],data[2],data[3],data[4]);

        myCloud.displayList();
        myCloud.drawList(graphics);

    }

}