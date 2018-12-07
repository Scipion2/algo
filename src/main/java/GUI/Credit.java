package GUI;

import javax.swing.*;

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

}
