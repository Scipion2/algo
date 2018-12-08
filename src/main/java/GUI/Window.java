package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

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

    }

    /*DAUGHTER CLASSES*/


        class EditListener implements ActionListener
        {

            public void actionPerformed(ActionEvent src)
            {}

        }


        class OptionListener implements ActionListener
        {

            int numberChoices=5;
            JFormattedTextField number_points=new JFormattedTextField(NumberFormat.getIntegerInstance());
            JFormattedTextField min_x=new JFormattedTextField(NumberFormat.getIntegerInstance());
            JFormattedTextField max_x=new JFormattedTextField(NumberFormat.getIntegerInstance());
            JFormattedTextField min_y=new JFormattedTextField(NumberFormat.getIntegerInstance());
            JFormattedTextField max_y=new JFormattedTextField(NumberFormat.getIntegerInstance());


            public void actionPerformed(ActionEvent src)
            {

                int optionsWidth=(winWidth*10)/100;
                int optionsHeight=(winHeight*90)/100;
                JPanel options=new JPanel();
                options.setBorder(BorderFactory.createLineBorder(Color.black));
                options.setPreferredSize(new Dimension(optionsWidth,optionsHeight));

                JPanel choice=new JPanel();
                choice.setLayout(new GridLayout(numberChoices,1));

                int choices_width=(optionsWidth*90)/100;
                int choices_height=(optionsHeight*3)/100;

                number_points.setPreferredSize(new Dimension(choices_width,choices_height));
                number_points.setText("Number of Points");

                min_x.setPreferredSize(new Dimension(choices_width,choices_height));
                min_x.setText("Abscisse minimale");

                max_x.setPreferredSize(new Dimension(choices_width,choices_height));
                max_x.setText("Abscisse maximale");

                min_y.setPreferredSize(new Dimension(choices_width,choices_height));
                min_y.setText("Ordonnee minimale");

                max_y.setPreferredSize(new Dimension(choices_width,choices_height));
                max_y.setText("Ordonnee maximale");

                Button accept=new Button("accept");

                container.add(options, BorderLayout.WEST);
                    options.add(choice,BorderLayout.CENTER);
                        choice.add(number_points);
                        choice.add(min_x);
                        choice.add(max_x);
                        choice.add(min_y);
                        choice.add(max_y);
                    options.add(accept,BorderLayout.SOUTH);

                 accept.addActionListener(new AcceptListener());

                 refresh();

            }

            class AcceptListener implements ActionListener
            {

                public void actionPerformed(ActionEvent src)
                {

                    String[] choices={null,null,null,null,null};
                    if(null==(choices[0]=number_points.getText()))
                        return;
                    if(null==(choices[1]=min_x.getText()))
                        return;
                    if(null==(choices[2]=max_x.getText()))
                        return;
                    if(null==(choices[3]=min_y.getText()))
                        return;
                    if(null==(choices[4]=max_y.getText()))
                        return;

                    body.go(choices);
                    body.repaint();

                }

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
