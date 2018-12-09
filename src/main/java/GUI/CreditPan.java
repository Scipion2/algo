package GUI;

import javax.swing.*;
import java.awt.*;

public class CreditPan extends JPanel
{

    String src = "C:\\Users\\Gaming\\IdeaProjects\\alg\\src\\main\\img\\back.jpg";
    String srcBis="/img/background";
    Image image;

    public CreditPan()
    {

        //image=(new javax.swing.ImageIcon(getClass().getResource(src))).getImage();

    }

    public void paintComponent(Graphics graphics)
    {


               // graphics.drawImage(image,0,0,null);
                super.paintComponent(graphics);

    }

}

