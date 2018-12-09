package GUI;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

public class Credit extends JFrame
{


    public Credit()
    {

        this.setTitle("Credits");
        this.setSize(1500, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        setContentPane(new AfficheImage("C:\\impress2.gif"));
        getContentPane().setLayout(new BorderLayout());

       // CreditPan back=new CreditPan();

       // setContentPane(back);
        this.setVisible(true);

    }


    public void closeWindow()
    {

        try
        {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.dispose();

    }



    class AfficheImage extends JPanel
    {
        Image eau;

        AfficheImage(String s)
        {
            eau = getToolkit().getImage(s);
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(eau, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
