package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.NumberFormat;
import triangulation.Point;

public class Window extends JFrame
{

    private Panel body = new Panel();
    private JPanel buttonContainer = new JPanel();
    private Button edit = new Button("Edit");
    private Button option = new Button("New");
    private Button exit=new Button("Exit");
    private JPanel container = new JPanel();
    int winWidth=1500;
    int winHeight=1000;

    public Window()
    {

        this.setTitle("Triangulation");
        this.setSize(winWidth, winHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
            container.add(buttonContainer, BorderLayout.NORTH);
                buttonContainer.setBorder(BorderFactory.createLineBorder(Color.black));
                buttonContainer.add(option);
                buttonContainer.add(edit);
                buttonContainer.add(exit);
            container.add(body, BorderLayout.CENTER);

        edit.addActionListener(new EditListener());
        option.addActionListener(new OptionListener());
        exit.addActionListener(new ExitListener());


        this.setContentPane(container);
        this.setVisible(true);

        this.addComponentListener(new ComponentAdapter()
        {

            public void componentResized(ComponentEvent e)
            {

                winHeight=getHeight();
                winWidth=getWidth();

            }
        });

    }

    /*DAUGHTER CLASSES*/


        class EditListener implements ActionListener
        {

            public void actionPerformed(ActionEvent src)
            {}

        }


        class OptionListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                body.repaint();

                ZDialog zd = new ZDialog(null, "Parametrage", true);

                ZDialogInfo zInfo = zd.showZDialog();

                JOptionPane jop = new JOptionPane();

                jop.showMessageDialog(null, zInfo.toString(), "Parametres", JOptionPane.INFORMATION_MESSAGE);

                body.go(zInfo.get());


            }

        }




        class ExitListener implements ActionListener
        {

            public void actionPerformed(ActionEvent src)
            {

                closeWindow(1);

            }

        }

    /*FUNCTIONS*/

    public void refresh()
    {

        this.setVisible(true);

    }

    public void closeWindow(int mode)
    {

        if(0==mode)
        this.dispose();

        if(1==mode)
        {

            this.setVisible(false);
            Credit credit= new Credit();
            credit.setVisible(true);
            credit.closeWindow();
            this.dispose();

        }

    }


    public  void closeWindow(JFrame newWindow)
    {

        this.setVisible(false);
        newWindow.setVisible(true);
        this.dispose();

    }

}

