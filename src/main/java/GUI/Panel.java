package GUI;

import triangulation.GeneratePoints;

import java.awt.*;
import javax.swing.JPanel;



public class Panel extends JPanel
{

    private String[] values;
    private boolean cloud=false;

    public void go(String[] values)
    {

       this.values=values;
       cloud=true;

    }

    public void paintComponent(final Graphics graphics)
    {

        super.paintComponent(graphics);

        if(cloud==true)
        {

            int[] data=new int[5];
            for(int i=0;i<5;++i)
            {

                data[i]=Integer.parseInt(values[i]);

            }

            GeneratePoints myCloud=new GeneratePoints(data[0],data[1],data[2],data[3],data[4]);
            int space=1;

            myCloud.drawList(getGraphics(),space);

            cloud=false;

        }

    }

}