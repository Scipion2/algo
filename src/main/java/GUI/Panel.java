package GUI;


import triangulation.GeneratePoints;
import triangulation.Point;
import triangulation.Triangulation;
import javax.swing.JPanel;



class Panel extends JPanel
{

    void go(String[] values)
    {

        ///String[] values1 = values;
        int[] data=new int[5];
        for(int i=0;i<5;++i)
        {

            data[i]=Integer.parseInt(values[i]);

        }

        Triangulation triang=new Triangulation(data[0],data[1],data[2],data[3],data[4]);

        int factorx=this.getWidth()/data[2];
        int factory=this.getHeight()/data[4];


        triang.drawTriang(getGraphics(),factorx,factory);

    }

   /* public void go(String[] values)
    {

       this.values=values;
        int[] data=new int[5];
        for(int i=0;i<5;++i)
        {

            data[i]=Integer.parseInt(values[i]);

        }

        GeneratePoints myCloud=new GeneratePoints(data[0],data[1],data[2],data[3],data[4]);

        int factorx=this.getWidth()/data[2];
        int factory=this.getHeight()/data[4];

        myCloud.drawList(getGraphics(),factorx,factory);
        myCloud.displayList();

        Convexe tamere=new Convexe(myCloud.cloud);
        tamere.drawConvexe(getGraphics(),factorx,factory);


    }*/

}