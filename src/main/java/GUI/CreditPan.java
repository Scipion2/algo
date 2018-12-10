package GUI;

import javax.swing.*;
import java.awt.*;

public class CreditPan extends JPanel
{

    String src = "/amuhome/l14005238/IdeaProjects/algo/src/main/img/back.jpg";
    String srcBis="/img/background";
    Image image;

    public CreditPan()
    {

        image=(new javax.swing.ImageIcon(getClass().getResource(srcBis))).getImage();

    }

    public void paintComponent(Graphics graphics)
    {

                super.paintComponent(graphics);
                graphics.drawImage(image,0,0,null);


    }

}

