package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame
{

    private Panel body = new Panel();
    private int winWidth=1500;
    private int winHeight=1000;

    public Window()
    {

        this.setTitle("Triangulation");
        this.setSize(winWidth, winHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        JPanel buttonContainer = new JPanel();
        container.add(buttonContainer, BorderLayout.NORTH);
                buttonContainer.setBorder(BorderFactory.createLineBorder(Color.black));
        Button option = new Button("New");
        buttonContainer.add(option);
        Button edit = new Button("Edit");
        buttonContainer.add(edit);
        Button exit = new Button("Exit");
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

                //JOptionPane jop = new JOptionPane();

                JOptionPane.showMessageDialog(null, zInfo.toString(), "Parametres", JOptionPane.INFORMATION_MESSAGE);

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

    private void closeWindow(int mode)
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

