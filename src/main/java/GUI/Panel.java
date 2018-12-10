package GUI;

import triangulation.GeneratePoints;
import triangulation.Point;
import utils.Maths;

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
       //this.repaint();
        int[] data=new int[5];
        for(int i=0;i<5;++i)
        {

            data[i]=Integer.parseInt(values[i]);

        }

        GeneratePoints myCloud=new GeneratePoints(data[0],data[1],data[2],data[3],data[4]);
        Maths.Vector space=new Maths.Vector(new Point(data[2],data[4]),new Point(this.getWidth(),this.getHeight()));

        myCloud.drawList(getGraphics(),space);
        myCloud.displayList();

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
            Maths.Vector space=new Maths.Vector(new Point(data[2],data[4]),new Point(this.getWidth(),this.getHeight()));

            Point test1=new Point(0,0);
            test1.drawPoint(getGraphics());

            myCloud.drawList(getGraphics(),space);
            myCloud.displayList();

            Point test=new Point(1,1);
            test.drawPoint(getGraphics());

            cloud=false;

        }

    }

}